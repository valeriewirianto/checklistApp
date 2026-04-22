package org.example.checklistapp.response;

public class AddItemResponse {
    private Long item_id;
    private String title;
    private Boolean completed;
    /*
        SERIALIZATION: How does Jackson convert java obj --> JSON response?

       1. You return object from controller
       2. Spring → Jackson
       3. Jackson:
            Calls getters
            Builds JSON
            The constructor in java obj is not used at all
    *
    * */
    public AddItemResponse(Long item_id, String title, Boolean completed){
        this.item_id = item_id;
        this.title = title;
        this.completed = completed;
    }

    public Long getItem_id() {
        return this.item_id;
    }

    public void setItem_id(Long item_id){
        this.item_id = item_id;
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
