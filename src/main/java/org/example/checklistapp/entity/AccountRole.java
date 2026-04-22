package org.example.checklistapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class AccountRole {

    @Id
    @GeneratedValue
    private Long role_id;

    @Column(nullable = false) // either admin or user
    private String name;

    public AccountRole(){}

    public AccountRole(String name){
        this.name = name;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public Long getRole_id() {
        return role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
