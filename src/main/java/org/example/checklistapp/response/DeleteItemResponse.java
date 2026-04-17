package org.example.checklistapp.response;

public class DeleteItemResponse  extends ApiResponse{

    private Long id;

    public DeleteItemResponse(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
