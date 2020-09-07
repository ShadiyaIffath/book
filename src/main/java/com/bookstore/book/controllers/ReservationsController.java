package com.bookstore.book.controllers;

import com.bookstore.book.dto.BookDto;
import com.bookstore.book.dto.CreateReservationDto;
import com.bookstore.book.dto.ReservationDto;
import com.bookstore.book.services.BookService;
import com.bookstore.book.services.ReservationService;
import com.bookstore.book.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/reservation")
public class ReservationsController {

    @Autowired
    private BookService service;

    @Autowired
    private ReservationService reservationService;

    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

    @RequestMapping(value="/create/{id}", method = RequestMethod.GET)
    public ModelAndView bookDetail(@PathVariable("id") int id, BookDto bookDto){
        ModelAndView model = new ModelAndView("makeReservation");
        model.addObject("book", service.findBookById(id));
        model.addObject("resForm", new CreateReservationDto());
        model.addObject("date", DateUtil.getToday()  );
        model.addObject("edit",false);
        return model;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @RequestMapping(value="/create/{id}", method = RequestMethod.POST)
    public ModelAndView createReservation(@PathVariable("id") int id, CreateReservationDto reservationDto, HttpServletRequest request){
        ModelAndView model = new ModelAndView();
        Date reservedDate = DateUtil.getDateFromString(reservationDto.getDateReserved());
        reservedDate = DateUtil.addDays(reservedDate,7);
        reservationDto.setDateExpected(sdf.format(reservedDate));
        boolean successful = reservationService.createReservation(reservationDto,id,request);
        if(successful){
            model.setViewName("redirect:/");
        }
        else{
            model.addObject("resForm", reservationDto);
            model.addObject("error", true);
            model.addObject("book", service.findBookById(id));
            model.addObject("date", DateUtil.getToday()  );
            model.addObject("edit",false);
            model.setViewName("makeReservation");
        }
        return model;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/remove", method = RequestMethod.POST)
    public ModelAndView deleteReservation(@RequestParam("id") int id){
        ModelAndView model = new ModelAndView();
        reservationService.removeReservation(id);
        model.setViewName("redirect:../reservations");
        return model;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView showEditReservation(@PathVariable("id")int id){
        ModelAndView model = new ModelAndView("makeReservation");
        ReservationDto dto = reservationService.getReservationById(id);
        model.addObject("resForm", dto);
        model.addObject("book", dto.getBook());
        model.addObject("statuses",reservationService.getAllStatus() );
        model.addObject("edit",true);
        return model;
    }
}
