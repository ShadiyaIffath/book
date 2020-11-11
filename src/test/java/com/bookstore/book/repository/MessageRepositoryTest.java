package com.bookstore.book.repository;

import com.bookstore.book.entities.Account;
import com.bookstore.book.entities.Message;
import com.bookstore.book.repositories.InquiryRepository;
import com.bookstore.book.repositories.MessageRepository;
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
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MessageRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void findByAccount_EmailOrderByIdDesc_ReturnsCollection(){
        //arrange
        Account x = new Account(null,"X","X","X",0,"X","X",true,null,null,null);
        x = entityManager.persistAndFlush(x);
        Message m1 = new Message(null,x,new Date(),"Test","test","test", true);
        Message m2 = new Message(null, x, new Date(),"Test","Test","Test",true);
        entityManager.persistAndFlush(m1);
        entityManager.persistAndFlush(m2);

        //act
        Collection<Message> test = messageRepository.findByAccount_EmailOrderByIdDesc("X");
        //assert
        assertEquals(test.size(),2);
    }
    @Test
    public void findByAccount_EmailOrderByIdDesc_ReturnsNull(){
        //act
        Collection<Message> test = messageRepository.findByAccount_EmailOrderByIdDesc("X");
        //assert
        assertEquals(test.size(),0);
    }

//    @Test
//    public void markMessageAsRead_WhenIdExists(){
//        //arrange
//        Account x = new Account(null,"X","X","X",0,"X","X",true,null,null,null);
//        Message m1 = new Message(null,x,new Date(),"Test","test","test", true);
//        m1 = entityManager.persistAndFlush(m1);
//        //act
//        messageRepository.markMessageAsRead(m1.getId());
//        //assert
//        m1 = entityManager.find(Message.class,m1.getId());
//        assertFalse(m1.isUnread());
//    }

    @Test
    public void countByAccount_IdAndUnread_returnsCount(){
        Account x = new Account(null,"X","X","X",0,"X","X",true,null,null,null);
        x = entityManager.persistAndFlush(x);
        Message m1 = new Message(null,x,new Date(),"Test","test","test", true);
        Message m2 = new Message(null, x, new Date(),"Test","Test","Test",true);
        entityManager.persistAndFlush(m1);
        entityManager.persistAndFlush(m2);

        //act
        Long count = messageRepository.countByAccount_IdAndUnread(x.getId(),true);
        //assert
        assertEquals(count,new Long(2));
    }

}
