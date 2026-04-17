package org.example.checklistapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(max = 20)
    private String username;

    @Column(nullable = false)
    private String password; //TODO: hash this

    public Account(){}

    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
