package com.bookstore.book.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 15, nullable=false, unique = true)
    private String ISBN;

    @Column(length = 50, nullable = false)
    private String title;

    @Column( nullable = false)
    private boolean available;

    @Column( nullable = false)
    private double value;

    @Column(nullable = false)
    private int quantity;

    @Column(length = 25, nullable = false)
    private String author;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;

    @Lob
    @Type(type="org.hibernate.type.ImageType")
    private byte[] image;
}
