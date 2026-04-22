package org.example.checklistapp.exception;

public class RoleDoesNotExistException extends RuntimeException {
    public RoleDoesNotExistException(String message) {
        super("Role " + message + " does not exist.");
    }
}
