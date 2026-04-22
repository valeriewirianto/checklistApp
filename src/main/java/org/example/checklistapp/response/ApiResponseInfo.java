package org.example.checklistapp.response;

public class ApiResponseInfo {

    private String message;

    public ApiResponseInfo(){}

    public ApiResponseInfo(String message){
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
