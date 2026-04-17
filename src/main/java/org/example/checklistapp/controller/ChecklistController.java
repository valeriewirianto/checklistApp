package org.example.checklistapp.controller;

import jakarta.validation.Valid;
import org.example.checklistapp.request.AddItemRequest;
import org.example.checklistapp.request.DeleteItemRequest;
import org.example.checklistapp.request.UpdateItemRequest;
import org.example.checklistapp.response.*;
import org.example.checklistapp.service.ChecklistService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checklist/item")
public class ChecklistController {

    private ChecklistService checklistService;

    public ChecklistController(ChecklistService checklistService){
        this.checklistService = checklistService;
    }

    @PostMapping
    public AddItemResponse addChecklistItem(@RequestBody @Valid AddItemRequest request){
        return this.checklistService.addChecklistItem(request);
    }

    @GetMapping("/{id}")
    public ChecklistItems getChecklistItemById(@PathVariable Long id){
        return this.checklistService.getItemById(id);
    }

    @GetMapping
    public GetAlIItemsResponse getAllChecklistItems(){
        return this.checklistService.getAllChecklistItems();
    }

    @DeleteMapping
    public DeleteItemResponse deleteChecklistItem(@RequestBody @Valid DeleteItemRequest request){
        return this.checklistService.deleteChecklistItem(request);
    }

    @PatchMapping
    public UpdateItemResponse updateChecklistItem(@RequestBody @Valid UpdateItemRequest request){
        return this.checklistService.updateChecklistItem(request);
    }

}
