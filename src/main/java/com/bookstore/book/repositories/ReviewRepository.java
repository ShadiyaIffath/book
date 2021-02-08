package com.bookstore.book.repositories;

import com.bookstore.book.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Collection<Review> findAllByBook_IdOrderByIdDesc(int id);
}
