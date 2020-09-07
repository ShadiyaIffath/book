package com.bookstore.book.controllers;

import com.bookstore.book.dto.BookDto;
import com.bookstore.book.dto.CreateAccountDto;
import com.bookstore.book.services.BookService;
import com.bookstore.book.services.ReservationService;
import com.bookstore.book.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ReservationService reservationService;


    private String[] colors = {"776d8a","f3e6e3","dbe3e5","d3c09a","fabea7","fbe2e5","9cada4","cff6cf","faf0af"};

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String welcome(Model model){
        model.addAttribute("colors",colors);
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("genre",bookService.getAllGenre());
        return "index"; //name of index.jsp
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showRegistration(Model model) {
        model.addAttribute("accountForm", new CreateAccountDto());
        return "register";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String showLogin(Model model, String error, String logout,String register, String unauthorized) {
        if (error != null)
            model.addAttribute("error", true);

        if (logout != null)
            model.addAttribute("logout", true);
        if(register != null)
            model.addAttribute("register", true);
        if(unauthorized != null)
            model.addAttribute("unauthorized",true);
        return "login";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/books", method= RequestMethod.GET)
    public String showBooks(Model model){
        List<BookDto> filtered = new ArrayList<>();
        model.addAttribute("books",bookService.getAllBooks());
        model.addAttribute("genres", bookService.getAllGenre());
        model.addAttribute("filtered", filtered);
        return "manageBooks";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/reservations", method= RequestMethod.GET)
    public String showReservations(Model model){
        model.addAttribute("reservations", reservationService.getAllReservations());
        model.addAttribute("statuses",reservationService.getAllStatus() );
        return "manageReservations";
    }
}
