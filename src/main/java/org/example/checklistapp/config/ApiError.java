package org.example.checklistapp.config;

import java.time.LocalDateTime;
import java.util.Map;

public class ApiError {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private Map<String, String> errors;

    public ApiError(int status, String error, String message, String path) {
        this(status, error, message, path, null);
    }

    public ApiError(int status, String error, String message,
                    String path, Map<String, String> errors) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.errors = errors;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public int getStatus(){
        return this.status;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public void setErrors(Map<String, String> errors){
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

}