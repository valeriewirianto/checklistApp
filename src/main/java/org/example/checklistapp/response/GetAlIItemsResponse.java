package org.example.checklistapp.response;

import java.util.List;

public class GetAlIItemsResponse {
    private Long user_id;
    private List<ChecklistItems> checklistItemResponse;

    public GetAlIItemsResponse(Long id, List<ChecklistItems> checklistItemResponse){
        this.user_id = id;
        this.checklistItemResponse = checklistItemResponse;
    }

    public Long getUser_id(){
        return this.user_id;
    }

    public List<ChecklistItems> getChecklistItemResponse(){
        return this.checklistItemResponse;
    }

}
