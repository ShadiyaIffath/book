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
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 1000)
    private String review;
    private float rating;
    private Date dateCreated;

    @ManyToOne()
    @JoinColumn(name="account_id")
    private Account account;

    @ManyToOne()
    @JoinColumn(name="book_id")
    private Book book;

}
