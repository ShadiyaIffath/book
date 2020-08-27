package com.bookstore.book.controllers;

import com.bookstore.book.dto.CreateAccountDto;
import com.bookstore.book.dto.CreateBookDto;
import com.bookstore.book.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String welcome(@RequestParam(value = "name", defaultValue = "World",
            required = true) String name, Model model){
        model.addAttribute("name", name);
        return "index"; //name of index.jsp
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showRegistration(Model model) {
        model.addAttribute("accountForm", new CreateAccountDto());
        return "register";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String showLogin(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", true);

        if (logout != null)
            model.addAttribute("logout", true);

        return "login";
    }

    @RequestMapping(value="/createBook", method = RequestMethod.GET)
    public String showCreateBook(Model model) {
        model.addAttribute("bookForm", new CreateBookDto());
        model.addAttribute("genres", bookService.getAllGenre());
        return "book";
    }
}
