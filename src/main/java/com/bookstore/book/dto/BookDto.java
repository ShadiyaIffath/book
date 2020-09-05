package com.bookstore.book.dto;

public class BookDto {
    private int id;
    private String title;
    private String ISBN;
    private String author;
    private double value;
    private String summary;
    private boolean available;
    private GenreDto genreDto;
    private int genreId;
    private byte[] image;
    private String imageString;

    public BookDto(){}

    public BookDto(int id, String title, String ISBN, String author, double value, String summary, boolean available, GenreDto genreDto, int genreId, byte[] image, String imageString) {
        this.id = id;
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
        this.value = value;
        this.summary = summary;
        this.available = available;
        this.genreDto = genreDto;
        this.genreId = genreId;
        this.image = image;
        this.imageString = imageString;
    }

    public BookDto(int id, String title, String ISBN, String author, double value, String summary, boolean available, GenreDto genreDto, int genreId, byte[] image) {
        this.id = id;
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
        this.value = value;
        this.summary = summary;
        this.available = available;
        this.genreDto = genreDto;
        this.genreId = genreId;
        this.image = image;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
