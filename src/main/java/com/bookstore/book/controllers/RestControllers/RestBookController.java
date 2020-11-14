package com.bookstore.book.controllers.RestControllers;

import com.bookstore.book.dto.BookDto;
import com.bookstore.book.dto.CreateBookDto;
import com.bookstore.book.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books-rest")
public class RestBookController {

    @Autowired
    private BookService bookService;

    @GetMapping("all-books")
    public List<BookDto> GetAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("newest-books")
    public List<BookDto> GetNewestBooks(){
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
}
