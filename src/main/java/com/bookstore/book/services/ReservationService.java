package com.bookstore.book.services;

import com.bookstore.book.dto.CreateReservationDto;
import com.bookstore.book.entities.Reservation;
import com.bookstore.book.repositories.BookRepository;
import com.bookstore.book.repositories.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BookRepository bookRepository;

    public void createReservation(CreateReservationDto reservationDto, int id){
        Reservation reservation = modelMapper.map(reservationDto, Reservation.class);
        reservation.setBook(bookRepository.findById(id));
    }
}
