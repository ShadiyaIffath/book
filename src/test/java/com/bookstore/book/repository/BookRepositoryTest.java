package com.bookstore.book.repository;

import com.bookstore.book.entities.Book;
import com.bookstore.book.entities.Genre;
import com.bookstore.book.repositories.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void whenFindByISBN_returnNull() {
        //act
        Book book = bookRepository.findByISBN("xxxxx");
        //assert
        assertNull(book);
    }

    @Test
    public void whenFindByISBN_returnBook() {
        //arrange
        //act
        Book found = bookRepository.findByISBN("1234");
        //assert
        assertNotNull(found);
    }

    @Test
    public void whenFindByID_returnNull() {
        //act
        Book book = bookRepository.findById(1);
        //assert
        assertNull(book);
    }

    @Test
    public void whenFindByID_returnBook() {
        //arrange
        //act
        Book found = bookRepository.findById(69);
        //assert
        assertNotNull(found);
    }
}
