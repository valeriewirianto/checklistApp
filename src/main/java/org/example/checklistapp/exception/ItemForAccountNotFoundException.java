package org.example.checklistapp.exception;

import org.example.checklistapp.entity.Account;

public class ItemForAccountNotFoundException extends RuntimeException {
    public ItemForAccountNotFoundException(String message) {
        super(message);
    }

    public ItemForAccountNotFoundException(Account account, Long item_id){
      super("Item id " + item_id + " does not exist for account " + account.getUsername());
    }
}
