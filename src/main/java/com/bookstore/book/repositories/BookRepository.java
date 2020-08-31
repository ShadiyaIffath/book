package com.bookstore.book.repositories;

import com.bookstore.book.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByISBN(String ISBN);
    Book findById(int id);
}

