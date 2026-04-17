package org.example.checklistapp.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class ChecklistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @Size(max = 50)
    private String title;

    @Column(nullable = false)
    private Boolean completed;

    @ManyToOne(fetch = FetchType.LAZY) // will store foreign key account_id in checklist item table
    // Note the OneToMany isde just JPA mapping in memory for easy access but does not duplicate data in DB
    @JoinColumn(name = "account_id")
    private Account account;

    public ChecklistItem(){

    }

    public ChecklistItem(String title, Boolean completed, Account account){
        this.title = title;
        this.completed = completed;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime time){
        this.createdAt = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted(){
        return this.completed;
    }

    public void setCompleted(Boolean completed){
        this.completed = completed;
    }

}
