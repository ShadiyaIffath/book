package com.bookstore.book.repositories;

import com.bookstore.book.entities.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface InquiryRepository extends JpaRepository<Inquiry, Integer> {
    Inquiry findInquiriesById(int id);

    Collection<Inquiry> findAllByOrderByIdDesc();
}
