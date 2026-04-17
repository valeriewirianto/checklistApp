package org.example.checklistapp.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddItemRequest {

    @NotBlank(message = "title is required")
    @Size(max = 50)
    private String title;

    private Boolean completed = false; // learning: Use Boolean obj not boolean primitive b/c boolean prim defaults to false. Boolean obj can be null

    /** DESERIALIZATION (JSON --> Java obj) How does a JSON request coming into the server get converted to this java object?
     * 1. JSON Request comes in
     * 2. Spring sees @RequestBody annotation and tells Jackson to convert it
     *to java obj
     *  3. Jackson then by default:
     *      1) Calls the default constructor (no args constructor)
     *      2) Calls any setters for any existing fields in the inbound request
     *              note: this WILL override any default values set during var initialization
     * **/

    public String getTitle() {
        return title;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
