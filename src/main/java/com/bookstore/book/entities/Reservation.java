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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date dateReserved;

    private Date dateCreated;

    private Date dateReturned;

    private Date dateExpected;

    @Column(length = 15, nullable = false)
    private String status;

    @ManyToOne()
    @JoinColumn(name="account_id")
    private Account account;

    @ManyToOne()
    @JoinColumn(name="book_id")
    private Book book;
}
