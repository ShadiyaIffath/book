package com.bookstore.book.repositories;

import com.bookstore.book.entities.Account;
import com.bookstore.book.entities.Book;
import com.bookstore.book.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Date;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query(value = "SELECT r from Reservation r  where r.status= ?1 and r.book.id =?4 and ((r.dateReserved <= ?2 and r.dateExpected >= ?2) or (r.dateReserved <= ?3 and r.dateExpected >= ?3))")
    Collection<Reservation> checkAvailability(String status, Date reservedDate, Date returnDate, int id);

    @Query(value = "SELECT r from Reservation r  where r.status= ?1 and r.book.id =?4 and r.id <> ?5 and ((r.dateReserved <= ?2 and r.dateExpected >= ?2) or (r.dateReserved <= ?3 and r.dateExpected >= ?3))")
    Collection<Reservation> checkReservationAvailability(String status, Date reservedDate, Date returnDate, int id, int reservationId);

    Reservation findById(int id);

    @Query(value= "SELECT r.book from Reservation r where r.id = ?1")
    Book getBookOfReservation(int id);

    Collection<Reservation> findByAccount_Email(String email);

    @Modifying
    @Query(value = "UPDATE Reservation SET status = ?2 where id = ?1")
    void cancelReservationById(int id, String status);
}