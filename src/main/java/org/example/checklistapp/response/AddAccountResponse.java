package org.example.checklistapp.response;

public class AddAccountResponse {

    private Long id;

    private String username;

    private String password;

    private String accountType;

    public AddAccountResponse(Long id, String username, String password, String accountType){
        this.id = id;
        this.username =username;
        this.password = password;
        this.accountType = accountType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }
}
