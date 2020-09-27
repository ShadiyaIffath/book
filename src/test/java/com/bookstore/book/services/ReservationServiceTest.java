package com.bookstore.book.services;

import com.bookstore.book.entities.Book;
import com.bookstore.book.repositories.BookRepository;
import com.bookstore.book.repositories.ReservationRepository;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @MockBean
    private ReservationRepository reservationRepository;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private AccountService accountService;

    @MockBean
    private MessageService messageService;

    @Before
    public void setUp(){
        Book book = new Book();
    }

    /**
     * Method: createReservation(CreateReservationDto reservationDto, int id)
     */
    @Test
    public void testCreateReservation() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getAllReservations()
     */
    @Test
    public void testGetAllReservations() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: removeReservation(int id)
     */
    @Test
    public void testRemoveReservation() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getReservationById(int id)
     */
    @Test
    public void testGetReservationById() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: updateReservation(ReservationDto dto)
     */
    @Test
    public void testUpdateReservation() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getBookFromReservationId(int id)
     */
    @Test
    public void testGetBookFromReservationId() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getAccountReservations()
     */
    @Test
    public void testGetAccountReservations() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: cancelReservation(int id)
     */
    @Test
    public void testCancelReservation() throws Exception {
//TODO: Test goes here... 
    }


    /**
     * Method: convertDto(CreateReservationDto dto)
     */
    @Test
    public void testConvertDto() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = ReservationService.getClass().getMethod("convertDto", CreateReservationDto.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: getReservationDto(Reservation x, ReservationDto reservationDto)
     */
    @Test
    public void testGetReservationDto() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = ReservationService.getClass().getMethod("getReservationDto", Reservation.class, ReservationDto.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: convertDtoToEntity(ReservationDto dto)
     */
    @Test
    public void testConvertDtoToEntity() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = ReservationService.getClass().getMethod("convertDtoToEntity", ReservationDto.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 
