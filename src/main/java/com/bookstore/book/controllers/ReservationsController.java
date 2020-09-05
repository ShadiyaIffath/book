package com.bookstore.book.controllers;

import com.bookstore.book.dto.BookDto;
import com.bookstore.book.dto.CreateReservationDto;
import com.bookstore.book.services.BookService;
import com.bookstore.book.services.ReservationService;
import com.bookstore.book.utils.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/reservation")
public class ReservationsController {

    @Autowired
    private BookService service;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private JwtUtils utils;

    @RequestMapping(value="/create/{id}", method = RequestMethod.GET)
    public ModelAndView bookDetail(@PathVariable("id") int id, BookDto bookDto){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ModelAndView model = new ModelAndView("makeReservation");
        model.addObject("book", service.findBookById(id));
        model.addObject("date",  df.format(new Date()) );
        return model;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @RequestMapping(value="/create/{id}", method = RequestMethod.POST)
    public ModelAndView createReservation(@PathVariable("id") int id,CreateReservationDto reservationDto, @RequestHeader(name="Authorization") String token){
        System.out.println(reservationDto.getBookId());
        System.out.println(reservationDto.getDateCreated());
        System.out.println(token);
        int accountId = utils.getIdFromJwtToken(token);
        System.out.println(accountId);
        ModelAndView model = new ModelAndView();
//        reservationService.createReservation(reservationDto,id);
        //TODO verify availability of the book
        return model;
    }
}
