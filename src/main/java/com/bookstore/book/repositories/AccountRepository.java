package com.bookstore.book.repositories;

import com.bookstore.book.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByEmail(String email);
}
