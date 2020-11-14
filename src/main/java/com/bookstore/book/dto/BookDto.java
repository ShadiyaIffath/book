package com.bookstore.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private int id;
    private String title;
    private String ISBN;
    private String author;
    private double value;
    private String summary;
    private String imageUrl;
    private boolean available;
    private GenreDto genreDto;
    private int genreId;
    private byte[] image;
    private String imageString;
    private String publisher;
}
