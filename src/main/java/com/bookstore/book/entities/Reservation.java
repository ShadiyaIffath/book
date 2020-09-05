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
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //The day the book will be collected by the user
    private Date dateReserved;

    //The day the reservation is made
    private Date dateCreated;

    //The day the book is returned
    private Date dateReturned;

    @Column(length = 15, nullable = false)
    private String status;

    @ManyToOne()
    @JoinColumn(name="account_id")
    private Account account;

    @ManyToOne()
    @JoinColumn(name="book_id")
    private Book book;
}
