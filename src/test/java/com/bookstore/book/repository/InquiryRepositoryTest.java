package com.bookstore.book.repository;

import com.bookstore.book.entities.Genre;
import com.bookstore.book.entities.Inquiry;
import com.bookstore.book.repositories.GenreRepository;
import com.bookstore.book.repositories.InquiryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InquiryRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private InquiryRepository inquiryRepository;

    @Test
    public void whenFindById_ReturnsInquiry(){
        //arrange
        Inquiry inquiry = new Inquiry(null,"Test","test",0,"Test",true);
        inquiry = entityManager.persistAndFlush(inquiry);
        //act
        Inquiry returned = inquiryRepository.findInquiriesById(inquiry.getId());
        //assert
        assertEquals(returned.getId(), inquiry.getId());
    }

    @Test
    public void whenFindByID_ReturnsNull(){
        //arrange
        //act
        Inquiry returned = inquiryRepository.findInquiriesById(1);
        //assert
        assertNull(returned);
    }
}
