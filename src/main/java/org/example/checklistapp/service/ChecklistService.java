package org.example.checklistapp.service;

import jakarta.transaction.Transactional;
import org.example.checklistapp.entity.Account;
import org.example.checklistapp.entity.ChecklistItem;
import org.example.checklistapp.exception.ItemForAccountNotFoundException;
import org.example.checklistapp.repository.AccountRepository;
import org.example.checklistapp.repository.ChecklistRepository;
import org.example.checklistapp.request.AddItemRequest;
import org.example.checklistapp.request.DeleteItemRequest;
import org.example.checklistapp.request.UpdateItemRequest;
import org.example.checklistapp.response.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ChecklistService {

    private final ChecklistRepository checklistRepository;
    private final AccountRepository accountRepository;

    public ChecklistService(ChecklistRepository checklistRepository, AccountRepository accountRepository){
        this.checklistRepository = checklistRepository;
        this.accountRepository = accountRepository;
    }
    public AddItemResponse addChecklistItem(AddItemRequest request){
        // Extract fields from request
        String title = request.getTitle();
        Boolean completed = request.isCompleted();

        // Get account from token
        Account account = getUsernameFromToken();

        // Business logic

        // Create new entity
        ChecklistItem checklistItem = new ChecklistItem(title, completed, account);

        ChecklistItem item = this.checklistRepository.save(checklistItem);

        if (item.getId() == null){ // failed to write to DB
            throw new DataIntegrityViolationException("Failed to write to database for id " + item.getId());
        }
        // Prepare response
        AddItemResponse response = new AddItemResponse(item.getId(), title, completed);
        return response;
    }

    public GetAlIItemsResponse getAllChecklistItems(){
        Account account = getUsernameFromToken();

        // Get all checklist items from DB table
        List<ChecklistItem> allChecklistItems = this.checklistRepository.findAllByAccountOrderByCreatedAtAsc(account);

        List<ChecklistItems> checklistItemResponse = new ArrayList<>();

        // Convert entity into response
        for (ChecklistItem item: allChecklistItems){
            checklistItemResponse.add(new ChecklistItems(item.getId(), item.getTitle(), item.getCompleted()));
        }

        // Prepare response
        GetAlIItemsResponse response = new GetAlIItemsResponse(account.getId(), checklistItemResponse);
        return response;
    }

    public ChecklistItems getItemById(@PathVariable Long itemId){
        Account account = getUsernameFromToken();

        // Find checklist item of matching itemId belonging to that user in database
        ChecklistItem item = this.getItemForAccount(itemId, account);

        // Prepare response
        ChecklistItems response = new ChecklistItems(item.getId(), item.getTitle(), item.getCompleted());
        return response;
    }

    public DeleteItemResponse deleteChecklistItem(DeleteItemRequest request){
        // Extract id of item to delete
        Long idToDelete = request.getId();

        Account account = getUsernameFromToken();

        // Find checklist item of matching itemId belonging to that user in database
        ChecklistItem item = this.getItemForAccount(idToDelete, account);

        this.checklistRepository.deleteById(item.getId());

        // Check deletion was successful
        if (this.checklistRepository.existsById(idToDelete)){
            throw new DataIntegrityViolationException("Item with id " + idToDelete + " failed to be deleted from the database.");
        }

        // Prepare response
        DeleteItemResponse response = new DeleteItemResponse(idToDelete);
        return response;
    }

    public UpdateItemResponse updateChecklistItem(UpdateItemRequest request){
        // extract fields from request
        Long idToUpdate = request.getId();
        String title = request.getTitle();
        Boolean completed = request.isCompleted();

        Account account = getUsernameFromToken();

        // Find checklist item of matching itemId belonging to that user in database
        ChecklistItem item = this.getItemForAccount(idToUpdate, account);

        // Update item with provided fields from request
        if (title != null){
            item.setTitle(title);
        }
        if (completed != null){
            item.setCompleted(completed);
        }

        this.checklistRepository.save(item);

        // Prepare response
        UpdateItemResponse response = new UpdateItemResponse(item.getId(), item.getTitle(), item.getCompleted());
        return response;
    }

    private Account getUsernameFromToken(){
        // Check username exists
        Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
        String username = authenticate.getName();

        Account account = this.accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found."));

        return account;
    }

    private ChecklistItem getItemForAccount(Long itemId, Account account){
        // Find checklist item of matching itemId belonging to that user in database
        ChecklistItem item = this.checklistRepository.findByIdAndAccount(itemId, account)
                .orElseThrow(() -> new ItemForAccountNotFoundException(account, itemId));

        return item;
    }
}
