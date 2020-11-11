package com.bookstore.book.repository;

import com.bookstore.book.entities.Genre;
import com.bookstore.book.repositories.GenreRepository;
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
public class GenreRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void whenFindById_ReturnsGenre(){
        //arrange
        Genre genre = new Genre(null,"Test","Test",null);
        Genre original = entityManager.persistAndFlush(genre);
        //act
        Genre returned = genreRepository.findById(original.getId()).get();
        //assert
        assertEquals(returned.getId(), original.getId());
    }

    @Test
    public void whenFindByID_ReturnsNull(){
        //arrange
        //act
        Genre found = genreRepository.findById(1);
        //assert
        assertNull(found);
    }
}
