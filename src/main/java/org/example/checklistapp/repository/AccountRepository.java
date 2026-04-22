package org.example.checklistapp.repository;

import org.example.checklistapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Boolean existsAccountByUsername(String username);

    Optional<Account> findByUsername(String username);

    Optional<Account> getAccountById(Long id);

}