package com.bookstore.book.dto;

import com.bookstore.book.entities.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private int id;
    private Account account;
    private Date dateCreated;
    private String title;
    private String message;
    private String type;
    private boolean unread;
}
