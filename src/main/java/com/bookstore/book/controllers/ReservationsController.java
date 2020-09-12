package com.bookstore.book.controllers;

import com.bookstore.book.dto.BookDto;
import com.bookstore.book.dto.CreateReservationDto;
import com.bookstore.book.dto.ReservationDto;
import com.bookstore.book.services.AccountService;
import com.bookstore.book.services.BookService;
import com.bookstore.book.services.ReservationService;
import com.bookstore.book.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/reservation")
public class ReservationsController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private AccountService accountService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(value="/create/{id}", method = RequestMethod.GET)
    public ModelAndView bookDetail(@PathVariable("id") int id, BookDto bookDto){
        ModelAndView model = new ModelAndView("makeReservation");
        model.addObject("book", bookService.findBookById(id));
        model.addObject("resForm", new CreateReservationDto());
        model.addObject("date", DateUtil.getToday()  );
        model.addObject("edit",false);
        return model;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @RequestMapping(value="/create/{id}", method = RequestMethod.POST)
    public ModelAndView createReservation(@PathVariable("id") int id, CreateReservationDto reservationDto){
        ModelAndView model = new ModelAndView();
        Date reservedDate = DateUtil.getDateFromString(reservationDto.getDateReserved());
        reservedDate = DateUtil.addDays(reservedDate,7);
        reservationDto.setDateExpected(sdf.format(reservedDate));
        boolean successful = reservationService.createReservation(reservationDto,id);
        if(successful){
            model.setViewName("redirect:/");
        }
        else{
            model.addObject("resForm", reservationDto);
            model.addObject("error", true);
            model.addObject("book", bookService.findBookById(id));
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
        System.out.println(dto.getAccount().getEmail());
        model.addObject("personal",accountService.verifyAccount(dto.getAccount().getEmail()));
        model.addObject("resForm", dto);
        model.addObject("book", dto.getBook());
        model.addObject("statuses",reservationService.getAllStatus() );
        model.addObject("edit",true);
        return model;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView editReservation(@PathVariable("id") int id, @RequestParam("personal") boolean personal, ReservationDto reservationDto, HttpServletRequest request){
        ModelAndView model = new ModelAndView();
        Date reservedDate = DateUtil.getDateFromString(reservationDto.getDateReserved());
        reservedDate = DateUtil.addDays(reservedDate,7);
        reservationDto.setDateExpected(sdf.format(reservedDate));
        boolean updateSuccessful = reservationService.updateReservation(reservationDto,request);
        if(updateSuccessful){
            model.setViewName("redirect:../../reservations");
        }else {
            editReservationModelView(id, reservationDto, model, personal);
        }
        return model;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @RequestMapping(value = "/ban", method = RequestMethod.POST)
    public ModelAndView cancelReservation(@RequestParam("id")int id){
        reservationService.cancelReservation(id);
        return new ModelAndView("redirect:../myReservations");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public ModelAndView deletePersonalReservation(@RequestParam("id") int id){
        reservationService.removeReservation(id);
        return new ModelAndView("redirect:../myReservations");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView editPersonalReservation(@PathVariable("id") int id, @RequestParam("personal") boolean personal, ReservationDto reservationDto, HttpServletRequest request){
        ModelAndView model = new ModelAndView();
        Date reservedDate = DateUtil.getDateFromString(reservationDto.getDateReserved());
        reservedDate = DateUtil.addDays(reservedDate,7);
        reservationDto.setDateExpected(sdf.format(reservedDate));
        boolean updateSuccessful = reservationService.updateReservation(reservationDto,request);
        if(updateSuccessful){
            model.setViewName("redirect:../../myReservations");
        }else {
            editReservationModelView(id, reservationDto, model, personal);
        }
        return model;
    }

    private void editReservationModelView(int id, ReservationDto reservationDto, ModelAndView model, boolean personal) {
        model.addObject("personal", personal);
        model.addObject("resForm", reservationDto);
        model.addObject("error", true);
        model.addObject("book", reservationService.getBookFromReservationId(id));
        model.addObject("statuses",reservationService.getAllStatus() );
        model.addObject("edit",true);
        model.setViewName("makeReservation");
    }

}
