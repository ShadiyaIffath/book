package com.bookstore.book.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name="account_id")
    private Account account;

    private Date dateCreated;

    @Column(length = 75, nullable = false)
    private String title;

    @Column(length = 500, nullable = false)
    private String message;

    @Column(length = 15, nullable = false)
    private String type;

    @Column(nullable = false)
    private boolean unread;
}
