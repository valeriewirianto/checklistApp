package org.example.checklistapp.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.checklistapp.entity.Account;
import org.example.checklistapp.exception.AuthenticationFailedException;
import org.example.checklistapp.exception.UsernameAlreadyExistsException;
import org.example.checklistapp.repository.AccountRepository;
import org.example.checklistapp.request.AddAccountRequest;
import org.example.checklistapp.request.AuthenticateRequest;
import org.example.checklistapp.response.AddAccountResponse;
import org.example.checklistapp.response.AuthenticateResponse;
import org.example.checklistapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccountService {

    AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public AccountService(AccountRepository repository, PasswordEncoder passwordEncoder){
        this.accountRepository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public AddAccountResponse addNewAccount(AddAccountRequest request){
        // Extract username and password from request
        String username = request.getUsername();
        String password = request.getPassword();

        // Check if username is already taken
        if (this.accountRepository.existsAccountByUsername(username)){
            throw new UsernameAlreadyExistsException(username);
        }

        // Hash password
        String hashedPassword = this.passwordEncoder.encode(password);

        System.out.println(password);
        System.out.println(hashedPassword);

        if (this.passwordEncoder.matches(password, hashedPassword)){
            System.out.println("match");
        }

        // Prepare entity and save to DB
        Account newAccount = new Account();
        newAccount.setUsername(username);
        newAccount.setPassword(hashedPassword);

        this.accountRepository.save(newAccount);

        // Prepare response
        AddAccountResponse response = new AddAccountResponse(username, password);
        return response;
    }

    public AuthenticateResponse authenticate(@Valid AuthenticateRequest request) {
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
        String token = jwtUtil.generateToken(username);

        // Prepare successful response
        AuthenticateResponse response = new AuthenticateResponse(username, token);
        return response;
    }
}
