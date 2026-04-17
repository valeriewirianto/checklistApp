package org.example.checklistapp.response;

import org.springframework.http.HttpStatusCode;

public class ErrorResponse {
    private HttpStatusCode statusCode;
    private String code;
    private String message; // this message is for telling the client what went wrong.
    // the seemingly redundant message in the /exception/Exception classes are for logging

    public ErrorResponse(HttpStatusCode status, String code, String message){
        this.statusCode = status;
        this.code = code;
        this.message = message;
    }

    // We need all these getters because this is how Jackson will convert from DTO Java obj to JSON
    // It looks for public getters in this class to put it in the body of the response back to client
    // Otherwise I kept getting generic 500 back
    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    public String getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }
}
