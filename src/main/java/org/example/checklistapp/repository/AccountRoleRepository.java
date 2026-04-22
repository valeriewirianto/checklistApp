package org.example.checklistapp.repository;

import org.example.checklistapp.entity.Account;
import org.example.checklistapp.entity.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRoleRepository extends JpaRepository<AccountRole, Long> {
    public Optional<AccountRole> findByName(String name);
}