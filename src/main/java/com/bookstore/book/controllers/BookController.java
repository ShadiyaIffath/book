package com.bookstore.book.controllers;

import com.bookstore.book.dto.CreateBookDto;
import com.bookstore.book.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {

    @Autowired
    private BookService service;

    @RequestMapping(value="/addBook", method = RequestMethod.GET)
    public ModelAndView registerBook(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("book");
        return modelAndView;
    }

    @RequestMapping(value="/addBook", method = RequestMethod.POST )
    public ModelAndView createBook(CreateBookDto createBookDto){
        boolean valid = service.isBookAlreadyAvailable(createBookDto.getISBN());
        ModelAndView modelAndView = new ModelAndView();
        if(valid == true){
            modelAndView.addObject("error",true);
            modelAndView.addObject("bookForm", createBookDto);
            modelAndView.addObject("genres", service.getAllGenre());
            modelAndView.setViewName("book");
        }
        else{
            service.saveBook(createBookDto);
            modelAndView.setViewName("redirect:");
        }
        return modelAndView;
    }
}
