package com.bookstore.book.controllers;

import com.bookstore.book.dto.CreateAccountDto;
import com.bookstore.book.entities.Account;
import com.bookstore.book.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    AccountService service;

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ModelAndView register( CreateAccountDto createAccountDto, RedirectAttributes redirectAttributes){
        boolean valid = service.isEmailInUse(createAccountDto.getEmail());
        ModelAndView model = new ModelAndView();

        if(valid == true){
            model.addObject("accountForm", createAccountDto);
            model.addObject("emailError", true);
            model.setViewName("register");
        }
        else{
            service.saveAccount(createAccountDto);
            redirectAttributes.addFlashAttribute("register",true);
            model.setViewName("redirect:login");
        }
        return model;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(){
        ModelAndView model = new ModelAndView();
        model.addObject("logout",true);
        model.setViewName("redirect:login");
        return model;
    }
}
