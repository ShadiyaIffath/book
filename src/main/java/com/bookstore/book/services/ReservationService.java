package com.bookstore.book.services;

import com.bookstore.book.dto.*;
import com.bookstore.book.entities.Book;
import com.bookstore.book.entities.Reservation;
import com.bookstore.book.repositories.BookRepository;
import com.bookstore.book.repositories.ReservationRepository;
import com.bookstore.book.utils.DateUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Collection;
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

    @Autowired
    private MessageService messageService;

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    public boolean createReservation(CreateReservationDto reservationDto, int id) {
        boolean valid = false;
        Reservation reservation = convertDto(reservationDto);
        int count = reservationRepository.checkAvailability("Created", reservation.getDateReserved(), reservation.getDateExpected(), id).size();
        reservation.setBook(bookRepository.findById(id));
        if ( count < reservation.getBook().getQuantity()) {
            reservation.setAccount(accountService.findLoggedInAccount());
            reservation = reservationRepository.save(reservation);
            messageService.reservationCreated(reservation.getAccount(),"Reservation Created", reservation);
            valid = true;
        }
        return valid;
    }

    public boolean newReservation(CreateReservationDto dto) {
        boolean valid = false;
        Reservation reservation = convertDto(dto);
        int count = reservationRepository.checkAvailability("Created", reservation.getDateReserved(), reservation.getDateExpected(), dto.getBookId()).size();
        reservation.setBook(bookRepository.findById(dto.getBookId()));
        if (count < reservation.getBook().getQuantity()) {
            reservation.setAccount(accountService.findAccountById(dto.getAccountId()));
            reservation = reservationRepository.save(reservation);
            messageService.reservationCreated(reservation.getAccount(), "Reservation Created", reservation);
            valid = true;
        }
        return valid;
    }

    public boolean validateAccountValidity(int id) {
        boolean valid = true;
        if (id != 1) {
            if (reservationRepository.findByAccount_IdAndStatus(id, "Created").size() > 3) {
                return false;
            }
        }
        return valid;
    }

    public List<ReservationDto> getAllReservations() {
        List<ReservationDto> reservations = reservationRepository.findAllByOrderByIdDesc()
                .stream().map(x -> {
                    ReservationDto dto = modelMapper.map(x, ReservationDto.class);
                    dto.setAccount(modelMapper.map(x.getAccount(),AccountDto.class));
                    dto.setBookDto(modelMapper.map(x.getBook(),BookDto.class));
                    return getReservationDto(x, dto);
                })
                .collect(Collectors.toList());
        return reservations;
    }

    public void removeReservation(int id) {
        Reservation reservation = reservationRepository.findById(id);
        messageService.reservationDeleted(reservation.getAccount(), "Reservation Deleted", reservation);
        reservationRepository.deleteById(id);
    }

    public ReservationDto getReservationById(int id) {
        Reservation x = reservationRepository.findById(id);
        ReservationDto reservationDto = modelMapper.map(x, ReservationDto.class);
        BookDto dto = modelMapper.map(x.getBook(),BookDto.class);
        reservationDto.setBookDto(dto);
        return getReservationDto(x, reservationDto);
    }

    public boolean updateReservation(ReservationDto dto) {
        boolean successful = false;
        try {
            Reservation reservation = convertDtoToEntity(dto);
            if (dto.getStatus().equals("Created")) {
                Collection<Reservation> reservationCollection = reservationRepository.checkReservationAvailability("Created", reservation.getDateReserved(), reservation.getDateExpected(), reservation.getBook().getId(), reservation.getId());
                if (reservationCollection == null || reservationCollection.size() < reservation.getBook().getQuantity()) {
                    reservationRepository.save(reservation);
                    messageService.reservationUpdated(reservation.getAccount(),"Reservation Updated", reservation);
                    successful = true;
                }
            } else if (dto.getStatus().equals("Completed")) {
                reservation.setDateReturned(DateUtil.getDateFromString(dto.getDateReturned()));
                messageService.reservationUpdated(reservation.getAccount(),"Reservation Updated", reservation);
                reservationRepository.save(reservation);
                successful = true;
            } else {
                messageService.reservationUpdated(reservation.getAccount(),"Reservation Updated", reservation);
                reservationRepository.save(reservation);
                successful = true;
            }
        } catch (Exception ex) {
            successful = false;
            logger.error(ex.getMessage());
        }
        return successful;
    }

    public BookDto getBookFromReservationId(int id){
        Book book = reservationRepository.getBookOfReservation(id);
        BookDto dto = modelMapper.map(book, BookDto.class);
        return dto;
    }

    public List<ReservationDto> getAccountReservations(){
        return reservationRepository.findByAccount_EmailOrderByIdDesc(accountService.findLoggedInAccountEmail())
                .stream().map(x -> {
                    ReservationDto dto = modelMapper.map(x, ReservationDto.class);
                    dto.setAccount(modelMapper.map(x.getAccount(),AccountDto.class));
                    dto.setBookDto(modelMapper.map(x.getBook(),BookDto.class));
                    return getReservationDto(x, dto);
                })
                .collect(Collectors.toList());
    }

    public List<ReservationDtoForAndroid> getAccountReservationsForAndroid(int accountId){
        return reservationRepository.findByAccount_IdOrderByIdDesc(accountId)
                .stream().map(x -> {
                    ReservationDtoForAndroid dto= modelMapper.map(x, ReservationDtoForAndroid.class);
                    dto.setAccountDto(modelMapper.map(x.getAccount(),AccountDto.class));
                    dto.setBookDto(modelMapper.map(x.getBook(),BookDtoForAndroid.class));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public List<ReservationDtoForAndroid> getAllReservationsForAndroid(){
        return reservationRepository.findAllByOrderByIdDesc()
                .stream().map(x -> {
                    ReservationDtoForAndroid dto= modelMapper.map(x, ReservationDtoForAndroid.class);
                    dto.setAccountDto(modelMapper.map(x.getAccount(),AccountDto.class));
                    dto.setBookDto(modelMapper.map(x.getBook(),BookDtoForAndroid.class));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void cancelReservation(int id){reservationRepository.cancelReservationById(id,"Cancelled");}

    public String[] getAllStatus() {
        return new String[]{"Created", "Cancelled", "Completed"};
    }

    private Reservation convertDto(CreateReservationDto dto) {
        Reservation reservation = new Reservation();
        reservation.setDateCreated(dto.getDateCreated());
        reservation.setDateReserved(DateUtil.getDateFromString(dto.getDateReserved()));
        reservation.setDateExpected(DateUtil.getDateFromString(dto.getDateExpected()));
        reservation.setStatus(dto.getStatus());
        return reservation;
    }

    private ReservationDto getReservationDto(Reservation x, ReservationDto reservationDto) {
        reservationDto.setDateCreated(DateUtil.getStringFromDate(x.getDateCreated()));
        reservationDto.setDateReserved(DateUtil.getStringFromDate(x.getDateReserved()));
        reservationDto.setDateExpected(DateUtil.getStringFromDate(x.getDateExpected()));
        if (x.getStatus().equals("Completed")) {
            reservationDto.setDateReturned(DateUtil.getStringFromDate(x.getDateReturned()));
        }
        return reservationDto;
    }

    private Reservation convertDtoToEntity(ReservationDto dto){
        Reservation reservation = reservationRepository.findById(dto.getId());
        reservation.setDateCreated(DateUtil.getDateFromString(dto.getDateCreated()));
        reservation.setDateReserved(DateUtil.getDateFromString(dto.getDateReserved()));
        reservation.setDateExpected(DateUtil.getDateFromString(dto.getDateExpected()));
        reservation.setStatus(dto.getStatus());
        if (reservation.getStatus().equals("Completed")) {
            reservation.setDateReturned(DateUtil.getDateFromString(dto.getDateReturned()));
        }
        return reservation;
    }
}
