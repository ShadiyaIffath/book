package com.bookstore.book.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDtoForAndroid {
    private int id;
    private String title;
    private String ISBN;
    private String author;
    private int quantity;
    private String summary;
    private String imageUrl;
    private boolean available;
    private GenreDto genreDto;
    private int genreId;
    private String publisher;
}
