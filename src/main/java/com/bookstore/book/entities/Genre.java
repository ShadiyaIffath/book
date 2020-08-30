package com.bookstore.book.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 15, nullable = false)
    private String genre;

    @Column(length = 25)
    private String description;

    @OneToMany( mappedBy = "genre",cascade = CascadeType.ALL)
    private List<Book> books;
}
