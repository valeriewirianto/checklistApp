package org.example.checklistapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private AccountRole role_id;

    public Account(){}

    public Account(String username, String password, AccountRole role_id){
        this.username = username;
        this.password = password;
        this.role_id = role_id;
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

    public AccountRole getRole_id() {
        return role_id;
    }

    public void setRole_id(AccountRole role_id) {
        this.role_id = role_id;
    }
}
