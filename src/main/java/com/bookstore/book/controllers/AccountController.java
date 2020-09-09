package com.bookstore.book.controllers;

import com.bookstore.book.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/ban", method= RequestMethod.POST)
    public ModelAndView banAccount(@RequestParam("id") int id, @RequestParam("ban") boolean ban){
        accountService.banAccountById(id, !ban);
        return new ModelAndView("redirect:../accounts");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/delete", method= RequestMethod.POST)
    public ModelAndView deleteAccount(@RequestParam("id") int id){
        accountService.deleteAccountById(id);
        return new ModelAndView("redirect:../accounts");
    }
}
