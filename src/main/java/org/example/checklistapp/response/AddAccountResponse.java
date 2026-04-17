package org.example.checklistapp.response;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;

public class AddAccountResponse {

    private String username;

    private String password; //TODO: hash this

    public AddAccountResponse(String username, String password){
        this.username =username;
        this.password = password;
    }

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
