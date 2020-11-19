package com.bookstore.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookDto {
    private String title;
    private String ISBN;
    private String description;
    private String author;
    private String publisher;
    private double value;
    private boolean available = true;
    private String summary;
    private String imageUrl;
    private MultipartFile image;
    private int genreId;
    private GenreDto genreDto;
    private String imageString;
}
