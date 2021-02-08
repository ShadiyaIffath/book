package com.bookstore.book.repository;

import com.bookstore.book.entities.Book;
import com.bookstore.book.entities.Review;
import com.bookstore.book.repositories.ReviewRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReviewRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void  findAllByBook_Id_ReturnReviewCollection() {
        //arrange
        //act
        Collection<Review> reviews = reviewRepository.findAllByBook_IdOrderByIdDesc(69);
        //assert
        assertNotNull(reviews);
    }

    @Test
    public void whenFindAllByBook_Id_ReturnNull(){
        //arrange
        //act
        Collection<Review> reviews = reviewRepository.findAllByBook_IdOrderByIdDesc(0);
        //assert
        assertNotNull(reviews);
    }
}
