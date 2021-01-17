package com.bookstore.book.restControllers;

import com.bookstore.book.dto.*;
import com.bookstore.book.entities.Book;
import com.bookstore.book.services.BookService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@WebAppConfiguration
public class RestBookControllerTest {
    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void GetBooksTest()throws Exception{
        //arrange
        List<BookDtoForAndroid> books = new ArrayList<>();
        when(bookService.getAllBooksForAndroid()).thenReturn(books);

        //act
        //assert
        mvc.perform(get("/books-rest/all-books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void GetNewestBooksTest()throws Exception{
        //arrange
        List<BookDtoForAndroid> books = new ArrayList<>();
        when(bookService.getNewestBooks()).thenReturn(books);

        //act
        //assert
        mvc.perform(get("/books-rest/newest-books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    public void DeleteBookTest()throws Exception{
        //arrange
        Mockito.doNothing().when(bookService).removeBook(Mockito.anyInt());
        //act
        //assert
        mvc.perform(delete("/books-rest/delete-book/{bookId}",10)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void GetAllBookReviewsTest()throws Exception{
        //arrange
        List<ReviewDtoForAndroid> reviews = new ArrayList<>();
        when(bookService.getAllReviewsForAndroid(Mockito.anyInt())).thenReturn(reviews);
        //act
        //assert
        mvc.perform(get("/books-rest/book-reviews/{bookId}",10)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @WithMockUser(roles = {"ADMIN","USER"})
    @Test
    public void NewReviewTest()throws Exception{
        //arrange
        CreateReviewDto dto = new CreateReviewDto("",4,null,1,1);
        when(bookService.saveReview(Mockito.any(CreateReviewDto.class))).thenReturn(new ReviewDtoForAndroid());
        Gson gson = new Gson();
        String json = gson.toJson(dto);
        //act
        //assert
        mvc.perform(post("/books-rest/new-review")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andReturn();
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    public void DeleteReviewTest()throws Exception{
        //arrange
        when(bookService.deleteReview(Mockito.anyInt())).thenReturn(new Book());
        //act
        //assert
        mvc.perform(delete("/books-rest/delete-review/{reviewId}",5)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void GetAllGenreTest()throws Exception{
        //arrange
        when(bookService.getAllGenre()).thenReturn(new ArrayList<GenreDto>());
        //act
        //assert
        mvc.perform(get("/books-rest/get-genre")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
    @WithMockUser(roles = {"ADMIN"})
    @Test
    public void AddBookTest_NullRequest()throws Exception{
        //act
        //assert
        mvc.perform(post("/books-rest/new-book")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    public void AddBookTest_Success()throws Exception{
        //arrange
        CreateBookDto bookDto = new CreateBookDto();
        Gson gson = new Gson();
        String json = gson.toJson(bookDto);
        Mockito.doNothing().when(bookService).newBook(Mockito.any(CreateBookDto.class));
        //act
        //assert
        mvc.perform(post("/books-rest/new-book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andReturn();
    }
}
