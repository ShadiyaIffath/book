package com.bookstore.book.repositories;

import com.bookstore.book.entities.Account;
import com.bookstore.book.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByEmail(String email);

    Account findById(int id);

    @Modifying
    @Query(value = "UPDATE Account SET active = ?2 where id = ?1")
    void banAccountById(int id, boolean active);

}
