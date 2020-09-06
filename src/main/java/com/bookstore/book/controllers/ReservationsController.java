package com.bookstore.book.controllers;

import com.bookstore.book.dto.BookDto;
import com.bookstore.book.dto.CreateReservationDto;
import com.bookstore.book.services.BookService;
import com.bookstore.book.services.ReservationService;
import com.bookstore.book.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/reservation")
public class ReservationsController {

    @Autowired
    private BookService service;

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(value="/create/{id}", method = RequestMethod.GET)
    public ModelAndView bookDetail(@PathVariable("id") int id, BookDto bookDto){
        ModelAndView model = new ModelAndView("makeReservation");
        model.addObject("book", service.findBookById(id));
        model.addObject("date", DateUtil.getToday()  );
        return model;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @RequestMapping(value="/create/{id}", method = RequestMethod.POST)
    public ModelAndView createReservation(@PathVariable("id") int id, CreateReservationDto reservationDto, HttpServletRequest request){
        ModelAndView model = new ModelAndView();
        boolean successful = reservationService.createReservation(reservationDto,id,request);
        if(successful){
            model.setViewName("redirect:/");
        }
        else{
            model.addObject("resForm", reservationDto);
            model.addObject("error", true);
            model.addObject("book", service.findBookById(id));
            model.addObject("date", DateUtil.getToday()  );
            model.setViewName("makeReservation");
        }
        return model;
    }

}
