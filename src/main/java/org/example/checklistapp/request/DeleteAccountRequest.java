package org.example.checklistapp.request;

import jakarta.validation.constraints.NotNull;

public class DeleteAccountRequest {

    @NotNull
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
