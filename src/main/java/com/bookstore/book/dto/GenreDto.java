package com.bookstore.book.dto;

public class GenreDto {
    private int id;
    private String genre;
    private String description;

    public GenreDto(){}

    public GenreDto(int id, String genre, String description) {
        this.id = id;
        this.genre = genre;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
