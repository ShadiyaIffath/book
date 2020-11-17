package com.bookstore.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDtoForAndroid {
    private int id;
    private String review;
    private float rating;
    private Date dateCreated;
    private BookDtoForAndroid bookDto;
    private AccountDto accountDto;
}
