package com.bookstore.book.dto;

import org.springframework.web.multipart.MultipartFile;

public class CreateBookDto {
    private String title;
    private String ISBN;
    private String description;
    private String author;
    private double value;
    private boolean available = true;
    private int quantity;
    private MultipartFile image;
    private int genreId;
    private String imageString;

    public CreateBookDto(){}

    public CreateBookDto(String title, String ISBN, String description, String author, double value, boolean available, int quantity, MultipartFile image, int genreId) {
        this.title = title;
        this.ISBN = ISBN;
        this.description = description;
        this.author = author;
        this.value = value;
        this.available = available;
        this.quantity = quantity;
        this.image = image;
        this.genreId = genreId;
    }

    public CreateBookDto(String title, String ISBN, String description, String author, double value, boolean available, int quantity, int genreId) {
        this.title = title;
        this.ISBN = ISBN;
        this.description = description;
        this.author = author;
        this.value = value;
        this.available = available;
        this.quantity = quantity;
        this.genreId = genreId;
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

}
