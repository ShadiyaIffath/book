package com.bookstore.book.services;

import com.bookstore.book.dto.BookDto;
import com.bookstore.book.dto.CreateReservationDto;
import com.bookstore.book.dto.GenreDto;
import com.bookstore.book.dto.ReservationDto;
import com.bookstore.book.entities.Reservation;
import com.bookstore.book.repositories.BookRepository;
import com.bookstore.book.repositories.ReservationRepository;
import com.bookstore.book.utils.DateUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AccountService accountService;

    public boolean createReservation(CreateReservationDto reservationDto, int id, HttpServletRequest request){
        boolean valid = false;
        Reservation reservation = convertDto(reservationDto);
        if(reservationRepository.checkAvailability("Created",reservation.getDateReserved(),reservation.getDateExpected())== null){
            reservation.setBook(bookRepository.findById(id));
            reservation.setAccount(accountService.findLoggedInAccount(request));
            reservationRepository.save(reservation);
            valid = true;
        }
        return valid;
    }
    public List<ReservationDto> getAllReservations() {
        List<ReservationDto> reservations = reservationRepository.findAll()
                .stream().map(x -> {
                    ReservationDto reservationDto = modelMapper.map(x, ReservationDto.class);
                    return reservationDto;
                })
                .collect(Collectors.toList());
        return reservations;
    }

    public String[] getAllStatus(){
        return new String[]{"Created","Cancelled","Completed"};
    }

    private Reservation convertDto(CreateReservationDto dto){
        Reservation reservation = new Reservation();
        reservation.setDateCreated(dto.getDateCreated());
        reservation.setDateReserved(dto.getDateReserved());
        reservation.setDateExpected(DateUtil.addDays(dto.getDateReserved(),7));
        reservation.setStatus(dto.getStatus());
        return  reservation;
    }
}
