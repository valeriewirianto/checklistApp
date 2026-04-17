package org.example.checklistapp.controller;

import jakarta.validation.Valid;
import org.example.checklistapp.request.AddAccountRequest;
import org.example.checklistapp.request.AuthenticateRequest;
import org.example.checklistapp.response.AddAccountResponse;
import org.example.checklistapp.response.AuthenticateResponse;
import org.example.checklistapp.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService service){
        this.accountService = service;
    }


    @PostMapping("/registration")
    public AddAccountResponse addAccount(@RequestBody @Valid AddAccountRequest request){
        return this.accountService.addNewAccount(request);
    }

    @PostMapping("/authenticate")
    public AuthenticateResponse authentication(@RequestBody @Valid AuthenticateRequest request){
        return this.accountService.authenticate(request);
    }
}
