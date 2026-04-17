package org.example.checklistapp.request;

import jakarta.validation.constraints.NotNull;

public class UpdateItemRequest {

    @NotNull
    private Long id;

    private String title;

    private Boolean completed = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
