package com.bookstore.book.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {

    @RequestMapping(value="/addBook", method = RequestMethod.POST)
    public ModelAndView registerBook(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("book");
        return modelAndView;
    }
}
