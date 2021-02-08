package com.bookstore.book.repositories;

import com.bookstore.book.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByISBN(String ISBN);
    Book findById(int id);

    List<Book> findTop4ByOrderByIdDesc();

    List<Book> findAllByOrderByIdDesc();
}

