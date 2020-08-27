package com.bookstore.book.dto;

public class CreateBookDto {
    private String title;
    private String ISBN;
    private String description;
    private String author;
    private double value;
    private boolean available;
    private GenreDto genreDto;

    public CreateBookDto(){}

    public CreateBookDto(String title, String ISBN, String description, String author, double value, boolean available, GenreDto genreDto) {
        this.title = title;
        this.ISBN = ISBN;
        this.description = description;
        this.author = author;
        this.value = value;
        this.available = available;
        this.genreDto = genreDto;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public GenreDto getGenreDto() {
        return genreDto;
    }

    public void setGenreDto(GenreDto genreDto) {
        this.genreDto = genreDto;
    }
}
