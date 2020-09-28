package com.bookstore.book.services;

import com.bookstore.book.dto.BookDto;
import com.bookstore.book.dto.MessageDto;
import com.bookstore.book.dto.ReservationDto;
import com.bookstore.book.entities.Account;
import com.bookstore.book.entities.Message;
import com.bookstore.book.entities.Reservation;
import com.bookstore.book.repositories.MessageRepository;
import com.bookstore.book.utils.enums.Status;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository repository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ModelMapper modelMapper;

    public void reservationCreated(Account account, String title, Reservation reservation) {
        Message message = new Message();
        message.setAccount(account);
        message.setTitle(title);
        message.setType("Created");
        message.setDateCreated(new Date());
        message.setUnread(true);
        String messageBody = "A reservation has been created by " + reservation.getAccount().getFirstName() + " " + reservation.getAccount().getLastName() + " at " + reservation.getDateCreated() + " the id is " + reservation.getId() + ". The reservation is made for " + reservation.getBook().getTitle() + " from " + reservation.getDateReserved() + " till " + reservation.getDateExpected();
        message.setMessage(messageBody);
        repository.save(message);
    }

    public void reservationDeleted(Account account, String title, Reservation reservation) {
        Message message = new Message();
        message.setAccount(account);
        message.setTitle(title);
        message.setType("Deleted");
        message.setDateCreated(new Date());
        message.setUnread(true);
        String messageBody = "A reservation has been deleted by " + reservation.getAccount().getFirstName() + " " + reservation.getAccount().getLastName() + " the id is " + reservation.getId() + ". The reservation was made for " + reservation.getBook().getTitle() + " from " + reservation.getDateReserved() + " till " + reservation.getDateExpected();
        message.setMessage(messageBody);
        repository.save(message);
    }

    public void reservationUpdated(Account account, String title, Reservation reservation) {
        Message message = new Message();
        message.setAccount(account);
        message.setTitle(title);
        message.setType("Updated");
        message.setDateCreated(new Date());
        message.setUnread(true);
        String messageBody = "A reservation has been updated by " + reservation.getAccount().getFirstName() + " " + reservation.getAccount().getLastName() + " at " + reservation.getDateCreated() + " the id is " + reservation.getId() + ". The reservation was made for " + reservation.getBook().getTitle() + " from " + reservation.getDateReserved() + " till " + reservation.getDateExpected();
        message.setMessage(messageBody);
        repository.save(message);
    }

    public List<MessageDto> getMessages() {
        String email = accountService.findLoggedInAccountEmail();
        return repository.findByAccount_EmailOrderByIdDesc(email).stream().map(x -> modelMapper.map(x, MessageDto.class)).collect(Collectors.toList());
    }

    @Transactional
    public void markMessageAsRead(int id) {
        repository.markMessageAsRead(id);
    }

    @Transactional
    public void markAllAsRead() {
        repository.markAllAsRead();
    }

    public void deleteMessage(int id) {
        repository.deleteById(id);
    }

    public int getUnreadCount() {
        Integer id = accountService.findLoggedInAccount().getId();
        return repository.countByAccount_IdAndUnread(id, true).intValue();
    }
}
