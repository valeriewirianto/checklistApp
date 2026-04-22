package org.example.checklistapp.response;

import java.util.List;

public class GetAllAccountsResponse {
    List<GetAccountResponse> allAccounts;

    public GetAllAccountsResponse(){

    }

    public List<GetAccountResponse> getAllAccounts() {
        return this.allAccounts;
    }

    public void setAllAccounts(List<GetAccountResponse> allAccounts) {
        this.allAccounts = allAccounts;
    }
}
