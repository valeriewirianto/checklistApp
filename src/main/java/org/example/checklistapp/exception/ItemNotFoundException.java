package org.example.checklistapp.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super(message);
    }

    public ItemNotFoundException(Long id){
        super("Item id " + id + " not found.");
    }
}
