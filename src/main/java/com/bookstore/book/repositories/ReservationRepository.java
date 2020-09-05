package com.bookstore.book.repositories;

import com.bookstore.book.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    //boolean checkAvailability(Reservation reservation);

}