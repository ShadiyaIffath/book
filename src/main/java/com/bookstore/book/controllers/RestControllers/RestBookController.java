package com.bookstore.book.controllers.RestControllers;

import com.bookstore.book.dto.*;
import com.bookstore.book.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books-rest")
public class RestBookController {

    @Autowired
    private BookService bookService;

    @GetMapping("all-books")
    public List<BookDtoForAndroid> GetAllBooks(){
        return bookService.getAllBooksForAndroid();
    }

    @GetMapping("newest-books")
    public List<BookDtoForAndroid> GetNewestBooks(){
        return bookService.getNewestBooks();
    }

    @PostMapping("add-book")
    public ResponseEntity AddBook(@RequestBody CreateBookDto bookDto){
        if(bookDto == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RZN000");
        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body("RZS000");
        }
        catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RZN001");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("delete-book")
    public ResponseEntity DeleteBook(@RequestBody int bookId){
        bookService.removeBook(bookId);
        return ResponseEntity.status(HttpStatus.OK).body("RZDR000");
    }

    @GetMapping("book-reviews/{bookId}")
    public List<ReviewDtoForAndroid> GetReviews(@PathVariable int bookId){
        return bookService.getAllReviewsForAndroid(bookId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @PostMapping("new-review")
    public ReviewDtoForAndroid SaveReview(@RequestBody CreateReviewDto dto){
        return bookService.saveReview(dto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("delete-review/{reviewId}")
    public ResponseEntity deleteReview(@PathVariable int reviewId){
        bookService.deleteReview(reviewId);
        return ResponseEntity.status(HttpStatus.OK).body("RZDR000");
    }

    @GetMapping("get-genre")
    public List<GenreDto> getAllGenre(){
        return bookService.getAllGenre();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("new-book")
    public ResponseEntity CreateNewBook(@RequestBody CreateBookDto dto){
        if(dto == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RZN000");
        }
        try{
           bookService.newBook(dto);
            return ResponseEntity.status(HttpStatus.OK).body("RZS000");
        }
        catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RZN001");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("edit-book")
    public ResponseEntity EditBook(@RequestBody BookDto dto){
        if(dto == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RZN000");
        }
        try{
            bookService.updateBook(dto);
            return ResponseEntity.status(HttpStatus.OK).body("RZS000");
        }
        catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RZN001");
        }
    }
}
