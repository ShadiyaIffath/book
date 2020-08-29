package com.bookstore.book.controllers;

import com.bookstore.book.dto.CreateBookDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {

    @RequestMapping(value="/addBook", method = RequestMethod.GET)
    public ModelAndView registerBook(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("book");
        return modelAndView;
    }

    @RequestMapping(value="/addBook", method = RequestMethod.POST )
    public ModelAndView createBook(@RequestParam("image") MultipartFile file, CreateBookDto createBookDto){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
