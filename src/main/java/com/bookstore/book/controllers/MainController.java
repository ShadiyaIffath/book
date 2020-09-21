package com.bookstore.book.controllers;

import com.bookstore.book.dto.BookDto;
import com.bookstore.book.dto.CreateAccountDto;
import com.bookstore.book.dto.CreateInquiryDto;
import com.bookstore.book.entities.Inquiry;
import com.bookstore.book.services.AccountService;
import com.bookstore.book.services.BookService;
import com.bookstore.book.services.InquiryService;
import com.bookstore.book.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private InquiryService inquiryService;


    private String[] colors = {"c3aed6","f3e6e3","dbe3e5","d3c09a","fabea7","fbe2e5","9cada4","cff6cf","faf0af"};

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String welcome(Model model){
        model.addAttribute("colors",colors);
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("genre",bookService.getAllGenre());
        return "index";
    }

    @RequestMapping(value="/{genre}", method = RequestMethod.GET)
    public String genreFilter(Model model, @PathVariable("genre") String genre){
        model.addAttribute("colors",colors);
        model.addAttribute("books", bookService.genreFilter(genre));
        model.addAttribute("genre",bookService.getAllGenre());
        return "index";
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

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String showContactUs(Model model){
        model.addAttribute("inquiry", new CreateInquiryDto());
        return "contact";
    }

    @RequestMapping(value="/search", method = RequestMethod.GET)
    public String search(Model model, @RequestParam("search") String search){
        model.addAttribute("colors",colors);
        model.addAttribute("books", bookService.searchFilter(search));
        model.addAttribute("genre",bookService.getAllGenre());
        return "index";
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
        return "manageReservations";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @RequestMapping(value="/myReservations", method= RequestMethod.GET)
    public String showAccountReservations(Model model){
        model.addAttribute("reservations", reservationService.getAccountReservations());
        return "myReservations";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/accounts", method= RequestMethod.GET)
    public String showAccounts(Model model){
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "manageAccounts";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @RequestMapping(value="/profile", method= RequestMethod.GET)
    public String showProfile(Model model){
        model.addAttribute("accounts", accountService.getLoggedInAccount());
        return "profile";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/inquiries", method= RequestMethod.GET)
    public String showInquiries(Model model){
        model.addAttribute("inquiries", inquiryService.getAllInquiries());
        return "inquiries";
    }

}
