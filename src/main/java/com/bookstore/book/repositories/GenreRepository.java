package com.bookstore.book.repositories;

import com.bookstore.book.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Genre findById(int id);
}
