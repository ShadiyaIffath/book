package com.bookstore.book.services;

import com.bookstore.book.entities.*;
import com.bookstore.book.repositories.InquiryRepository;
import com.bookstore.book.repositories.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageServiceTest {
    @Autowired
    private MessageService messageService;

    @MockBean
    private MessageRepository repository;

    @MockBean
    private AccountService accountService;

    @Test
    public void testReservationCreated() throws Exception {
        //arrange
        Account x = new Account(null,"X","X","X",0,
                "X","X",true,null,null,null);
        Book b = new Book();
        b.setTitle("");
        Reservation res = new Reservation(1,new Date(), new Date(), new Date(), new Date(),"",x,b);
        //act
        messageService.reservationCreated(x,"",res);

        //assert
        verify(repository, times(1)).save(any(Message.class));
    }

    @Test
    public void testReservationUpdated() throws Exception{
        //arrange
        Account x = new Account(null,"X","X","X",0,"X","X",true,null,null,null);
        Book b = new Book();
        b.setTitle("");
        Reservation res = new Reservation(1,new Date(), new Date(), new Date(), new Date(),"",x,b);
        //act
        messageService.reservationUpdated(x,"",res);

        //assert
        verify(repository, times(1)).save(any(Message.class));
    }

    @Test
    public void testGetMessages(){
        //arrange
        when(accountService.findLoggedInAccountEmail()).thenReturn("x@x.com");
        //act
        messageService.getMessages();

        //assert
        verify(repository, times(1)).findByAccount_EmailOrderByIdDesc("x@x.com");
    }
    @Test
    public void testGetMessagesById(){
        //act
        messageService.getMessages(1);
        //assert
        verify(repository,times(1)).findByAccount_IdOrderByIdDesc(1);
    }

    @Test
    public void testMarkMessageAsRead(){
        //act
        messageService.markMessageAsRead(1);
        //assert
        verify(repository,times(1)).markMessageAsRead(1);
    }
    @Test
    public void testMarkAllMessagesAsRead(){
        //act
        messageService.markAllAsRead(1);
        //assert
        verify(repository,times(1)).markAllAsRead(1);
    }
    @Test
    public void testDeleteMessage(){
        //act
        messageService.deleteMessage(1);
        //assert
        verify(repository,times(1)).deleteById(1);
    }

    @Test
    public void testGetUnreadMessageCount(){
        //arrange
        Account x = new Account(1,"X","X","X",
                0,"X","X",true,null,null,null);
        when(accountService.findLoggedInAccount()).thenReturn(x);
        //act
        messageService.getUnreadCount();

        //assert
        verify(repository, times(1)).countByAccount_IdAndUnread(1,true);
    }
}
