package org.example.checklistapp.exception;

public class AccountIdNotFoundException extends RuntimeException {
    public AccountIdNotFoundException(String message) {
        super(message);
    }
    public AccountIdNotFoundException(Long id) {
        super("Account with id " + id + " not found.");
    }
}
