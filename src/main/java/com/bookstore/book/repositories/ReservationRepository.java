package com.bookstore.book.repositories;

import com.bookstore.book.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query(value = "SELECT r from Reservation r  where r.status= ?1 and ((r.dateReserved <= ?2 and r.dateExpected >= ?2) or (r.dateReserved <= ?3 and r.dateExpected >= ?3))")
    Reservation checkAvailability(String status, Date reservedDate, Date returnDate);

}