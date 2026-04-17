package org.example.checklistapp.repository;

import org.example.checklistapp.entity.Account;
import org.example.checklistapp.entity.ChecklistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ChecklistRepository extends JpaRepository<ChecklistItem, Long> {

    // Custom query method (Spring generates SQL automatically)
    List<ChecklistItem> findByCompleted(Boolean completed);

    List<ChecklistItem> findByTitleContainingIgnoreCase(String title);

    Long id(Long id);

    List<ChecklistItem> findAllByOrderByCreatedAtAsc();

    List<ChecklistItem> findAllByAccountOrderByCreatedAtAsc(Account account);

    Optional<ChecklistItem> findByIdAndAccount(Long id, Account account);
}