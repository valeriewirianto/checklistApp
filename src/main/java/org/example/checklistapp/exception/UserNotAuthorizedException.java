package org.example.checklistapp.exception;

public class UserNotAuthorizedException extends RuntimeException {
    public UserNotAuthorizedException(String message) {
        super(message);
    }
}
