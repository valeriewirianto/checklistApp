package org.example.checklistapp.response;

public class GetAccountResponse {

    private Long id;

    private String username;

    private String accountType;

    public GetAccountResponse(){}

    public GetAccountResponse(Long id, String username, String accountType){
        this.id = id;
        this.username =username;
        this.accountType = accountType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
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
