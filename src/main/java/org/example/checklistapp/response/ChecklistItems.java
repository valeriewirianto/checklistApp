package org.example.checklistapp.response;

public class ChecklistItems {
    private Long id;
    private String title;
    private Boolean completed;

    public ChecklistItems(Long id, String title, Boolean completed){
        this.id = id;
        this.title = title;
        this. completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle(){
        return this.title;
    }

    public Boolean getCompleted(){
        return this.completed;
    }
}
