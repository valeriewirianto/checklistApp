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
}
