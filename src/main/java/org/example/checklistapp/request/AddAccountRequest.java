package org.example.checklistapp.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddAccountRequest {

    @NotBlank(message = "username is required")
    @Size(max = 20)
    private String username;

    @NotBlank(message = "password is required")
    @Size(max = 20)
    private String password; //TODO: hash this

    @NotBlank(message = "accountType is required")
    private String accountType;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
