package org.example.checklistapp.request;

import jakarta.validation.constraints.NotNull;

public class DeleteItemRequest {

    @NotNull(message = "id must not be null")
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
