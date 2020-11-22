package com.bookstore.book.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 15, nullable=false)
    private String ISBN;

    @Column(length = 50, nullable = false)
    private String title;

    @Column( nullable = false)
    private boolean available;

    @Column( nullable = false)
    private int quantity;

    @Column(length = 500,nullable = false)
    private String summary;

    @Column(length = 25, nullable = true)
    private String publisher;

    @Column(length = 25, nullable = false)
    private String author;

    @Column
    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @OneToMany( mappedBy = "book",cascade = CascadeType.ALL)
    private List<Review> reviews;

    @Lob
    @Type(type="org.hibernate.type.ImageType")
    private byte[] image;

    @OneToMany( mappedBy = "book",cascade = CascadeType.ALL)
    private List<Reservation> reservations;
}
