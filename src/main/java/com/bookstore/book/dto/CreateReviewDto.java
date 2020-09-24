package com.bookstore.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewDto {
    private String review;
    private float rating;
    private Date dateCreated = new Date();
    private int bookId;
}
