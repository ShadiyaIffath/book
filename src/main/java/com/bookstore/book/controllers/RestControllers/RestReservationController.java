package com.bookstore.book.controllers.RestControllers;

import com.bookstore.book.dto.*;
import com.bookstore.book.services.AccountService;
import com.bookstore.book.services.BookService;
import com.bookstore.book.services.ReservationService;
import javassist.bytecode.stackmap.BasicBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservation-rest")
public class RestReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private AccountService accountService;

    @PostMapping("new-reservation")
    public ResponseEntity AddReservation(@RequestBody CreateReservationDto Dto){
        if(Dto == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RZN000");
        }
        try{
            if(!reservationService.validateAccountValidity(Dto.getAccountId())){
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("RZAU002");
            }
            if(reservationService.newReservation(Dto)){
                return ResponseEntity.status(HttpStatus.OK).body("RZS000");
            }
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("RZAU001");
        }
        catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RZN001");
        }
    }
    @PostMapping("delete-reservation/{reservationId}")
    public ResponseEntity DeleteReservation(@PathVariable int reservationId){
        try {
            reservationService.removeReservation(reservationId);
            return ResponseEntity.status(HttpStatus.OK).body("RZDR000");
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RZN001");
        }
    }

    @PostMapping("cancel-reservation/{reservationId}")
    public ResponseEntity CancelReservation(@PathVariable int reservationId){
        try {
            reservationService.cancelReservation(reservationId);
            return ResponseEntity.status(HttpStatus.OK).body("RZDR000");
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RZN001");
        }
    }

    @GetMapping("all-reservations")
    public List<ReservationDtoForAndroid> GetAllReservations(){
        return reservationService.getAllReservationsForAndroid();
    }

    @GetMapping("account-reservations/{accountId}")
    public List<ReservationDtoForAndroid> GetAccountReservations(@PathVariable int accountId){
        return reservationService.getAccountReservationsForAndroid(accountId);
    }

    @PostMapping("edit-reservation")
    public ResponseEntity EditReservation(@RequestBody ReservationDto dto){
        try {
            boolean updateSuccessful = reservationService.updateReservation(dto);
            if (updateSuccessful) {
                return ResponseEntity.status(HttpStatus.OK).body("RZDR000");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("RZAU001");
            }
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RZN001");
        }
    }
}
