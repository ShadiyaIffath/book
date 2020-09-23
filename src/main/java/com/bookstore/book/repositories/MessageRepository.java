package com.bookstore.book.repositories;

import com.bookstore.book.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    Collection<Message> findByAccount_Email(String email);

    @Modifying
    @Query(value = "UPDATE Message SET unread = false where id = ?1")
    void markMessageAsRead(int id);

    @Modifying
    @Query(value = "UPDATE Message SET unread = false")
    void markAllAsRead();

    Long countByAccount_IdAndUnread(Integer id, boolean unread);
}
