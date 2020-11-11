package com.bookstore.book.repository;

import com.bookstore.book.entities.Book;
import com.bookstore.book.entities.Reservation;
import com.bookstore.book.repositories.ReservationRepository;
import com.bookstore.book.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReservationRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void checkAvailability_ReturnCollection(){
        //arrange
        Date date = DateUtil.getDateFromString("2020-09-29");
        //act
        Collection<Reservation> reservations = reservationRepository.checkAvailability("Created",date, DateUtil.addDays(date, 7),69);
        //assert
        assertNotNull(reservations);
    }

    @Test
    public void checkReservationAvailability_ReturnCollection(){
        //arrange
        Date date = DateUtil.getDateFromString("2020-09-29");
        //act
        Collection<Reservation> reservations  = reservationRepository.checkReservationAvailability("Created",date, DateUtil.addDays(date,7),69, 70);
        //assert
        assertNotNull(reservations);
    }

    @Test
    public void findByID_ReturnReservation(){
        //arrange
        //act
        Reservation reservation = reservationRepository.findById(70);

        assertNotNull(reservation);
    }

    @Test
    public void getBookOfReservation(){
        //arrange
        //act
        Book book = reservationRepository.getBookOfReservation(70);

        //assert
        assertEquals(book.getId(),(Integer)69);
    }

    @Test
    public void findByAccount_EmailOrderByIdDesc_ReturnCollection(){
        //arrange
        //act
        Collection<Reservation> reservations = reservationRepository.findByAccount_EmailOrderByIdDesc("a@a.com");
        //assert
        assertNotNull(reservations);
    }

    @Test
    public void cancelReservationById(){
        reservationRepository.cancelReservationById(70,"Cancelled");
        //assert
        Reservation reservation = reservationRepository.findById(70);
        assertEquals(reservation.getStatus(),"Cancelled");
    }
}
