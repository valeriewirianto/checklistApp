package org.example.checklistapp.service;

import io.jsonwebtoken.Claims;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.checklistapp.entity.Account;
import org.example.checklistapp.entity.AccountRole;
import org.example.checklistapp.exception.*;
import org.example.checklistapp.repository.AccountRepository;
import org.example.checklistapp.repository.AccountRoleRepository;
import org.example.checklistapp.request.AddAccountRequest;
import org.example.checklistapp.request.AuthenticateRequest;
import org.example.checklistapp.request.DeleteAccountRequest;
import org.example.checklistapp.response.*;
import org.example.checklistapp.util.JwtUtil;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class AccountService {

    AccountRepository accountRepository;
    AccountRoleRepository accountRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public AccountService(AccountRepository accountRepository, AccountRoleRepository accountRoleRepository, PasswordEncoder passwordEncoder){
        this.accountRepository = accountRepository;
        this.accountRoleRepository = accountRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AddAccountResponse addNewAccount(AddAccountRequest request){
        // Extract fields from request
        String username = request.getUsername();
        String password = request.getPassword();
        String accountType = request.getAccountType().equalsIgnoreCase("admin") ? "admin" : "user";

        // Check if username is already taken
        if (this.accountRepository.existsAccountByUsername(username)){
            throw new UsernameAlreadyExistsException(username);
        }

        // Hash password
        String hashedPassword = this.passwordEncoder.encode(password);

        // Prepare Account entity and save to DB
        AccountRole accountRole = this.accountRoleRepository.findByName(accountType).orElseThrow(
                () -> new RoleDoesNotExistException(accountType)
        );

        Account newAccount = new Account(username, password, accountRole);
        newAccount.setUsername(username);
        newAccount.setPassword(hashedPassword);

        this.accountRepository.save(newAccount);

        // Prepare response
        AddAccountResponse response = new AddAccountResponse(newAccount.getId(), username, password, accountType);
        return response;
    }

    public AuthenticateResponse authenticate(AuthenticateRequest request) {
        // Extract fields from request
        String username = request.getUsername();
        String password = request.getPassword();

        // Get existing user account of username or throw err if not exists
        Account account = this.accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found."));
        String hashedAccountPassword = account.getPassword();

        // Check if hashed password matches
        if (!this.passwordEncoder.matches(password, account.getPassword())){
            throw new AuthenticationFailedException("Authentication for user " + username + " failed.");
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(username, account.getId(), account.getRole_id().getName());

        // Prepare successful response
        AuthenticateResponse response = new AuthenticateResponse(username, token);
        return response;
    }

    private void deleteAccountById(Long idToDelete){
        Account accountToDelete = this.accountRepository.getAccountById(idToDelete)
                .orElseThrow(() -> new AccountIdNotFoundException("Account with id " + idToDelete + " not found."));
        this.accountRepository.deleteById(accountToDelete.getId());
    }

    // Get claims from token
    private Claims getClaims(){
        Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
        return (Claims) authenticate.getDetails();
    }

    private Boolean claimsIsAdmin(Claims claims){
        String role = claims.get("role", String.class);

        return role.equals("admin");
    }

    public DeleteAccountResponse deleteAccount(Long idToDelete) {
        Claims claims = getClaims(); // Extract claims from token

        if (claimsIsAdmin(claims)){ // user is admin, can delete any account
            this.deleteAccountById(idToDelete);

        } else { // user is not admin, can only delete its own account
            Long tokenId = claims.get("userId", Long.class);
            if (!Objects.equals(tokenId, idToDelete)){ // if user tries to delete another user's acocunt
                throw new UserNotAuthorizedException("User " + tokenId + " is not authorized to delete user " + idToDelete);
            }

            // try to delete its own account
            this.deleteAccountById(idToDelete);
        }

        // Prepare response and return 200
        DeleteAccountResponse response = new DeleteAccountResponse();
        response.setId(idToDelete);

        return response;
    }

    public GetAllAccountsResponse getAllAccounts(){
        Claims claims = this.getClaims();

        if (!this.claimsIsAdmin(claims)){ // if user is not admin, throw FORBIDDEN
            throw new UserNotAuthorizedException("User is not authorized to perform this action");
        }

        List<Account> allAccounts = this.accountRepository.findAll();

        // Prepare response
        GetAllAccountsResponse response = new GetAllAccountsResponse();
        List<GetAccountResponse> getAccountResponses = new ArrayList<>();
        GetAccountResponse accountResponse;

        for (Account account: allAccounts){
            accountResponse = new GetAccountResponse();
            accountResponse.setId(account.getId());
            accountResponse.setUsername(account.getUsername());
            accountResponse.setAccountType(account.getRole_id().getName());

            getAccountResponses.add(accountResponse);
        }

        response.setAllAccounts(getAccountResponses);
        return response;
    }
}
