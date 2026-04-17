package org.example.checklistapp.response;

public class AuthenticateResponse {
    private String username;
    private String token;

    public AuthenticateResponse(String username, String token){
        this.username = username;
        this.token = token;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return this.username;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}