package com.bookstore.book.services;

import com.bookstore.book.dto.BookDto;
import com.bookstore.book.dto.CreateReservationDto;
import com.bookstore.book.dto.ReservationDto;
import com.bookstore.book.entities.Reservation;
import com.bookstore.book.repositories.BookRepository;
import com.bookstore.book.repositories.ReservationRepository;
import com.bookstore.book.utils.DateUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
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

    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

    public boolean createReservation(CreateReservationDto reservationDto, int id, HttpServletRequest request){
        boolean valid = false;
        Reservation reservation = convertDto(reservationDto);
        if(reservationRepository.checkAvailability("Created",reservation.getDateReserved(),reservation.getDateExpected(),id)== null){
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
                    return getReservationDto(x, reservationDto);
                })
                .collect(Collectors.toList());
        return reservations;
    }

    public void removeReservation(int id) {
        reservationRepository.deleteById(id);
    }

    public ReservationDto getReservationById(int id){
        Reservation x =  reservationRepository.findById(id);
        ReservationDto reservationDto = modelMapper.map(x, ReservationDto.class);
        BookDto dto = reservationDto.getBook();
        dto.setImageString(Base64.getEncoder().encodeToString(dto.getImage()));
        reservationDto.setBook(dto);
        return getReservationDto(x, reservationDto);
    }

    public String[] getAllStatus(){
        return new String[]{"Created","Cancelled","Completed"};
    }

    private Reservation convertDto(CreateReservationDto dto){
        Reservation reservation = new Reservation();
        reservation.setDateCreated(dto.getDateCreated());
        reservation.setDateReserved(DateUtil.getDateFromString(dto.getDateReserved()));
        reservation.setDateExpected(DateUtil.getDateFromString(dto.getDateExpected()));
        reservation.setStatus(dto.getStatus());
        return  reservation;
    }

    private ReservationDto getReservationDto(Reservation x, ReservationDto reservationDto) {
        reservationDto.setDateCreated(sdf.format(x.getDateCreated()));
        reservationDto.setDateReserved(sdf.format(x.getDateReserved()));
        reservationDto.setDateExpected(sdf.format(x.getDateExpected()));
        if(x.getStatus().equals("Completed")) {
            reservationDto.setDateReturned(sdf.format(x.getDateReturned()));
        }
        return reservationDto;
    }
}
