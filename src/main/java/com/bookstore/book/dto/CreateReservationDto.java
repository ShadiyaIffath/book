package com.bookstore.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservationDto {

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date dateReserved;

    private Date dateCreated = new Date();

    private String status = "Created";

}
