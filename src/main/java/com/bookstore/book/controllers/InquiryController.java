package com.bookstore.book.controllers;

import com.bookstore.book.services.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/delete", method= RequestMethod.POST)
    public ModelAndView deleteInquiry(@RequestParam("id") int id){
        inquiryService.deleteInquiryById(id);
        return new ModelAndView("redirect:../inquiries");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/respond", method= RequestMethod.POST)
    public ModelAndView respondInquiry(@RequestParam("inquiryId") String id, @RequestParam("response") String response){
        inquiryService.sendResponseEmail(Integer.parseInt(id), response);
        return new ModelAndView("redirect:../inquiries");
    }
}
