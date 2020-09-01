package com.bookstore.book.controllers;

import com.bookstore.book.dto.CreateAccountDto;
import com.bookstore.book.services.AccountService;
import com.bookstore.book.services.AuthService;
import com.bookstore.book.utils.security.requests.AuthRequest;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    AccountService service;

    @Autowired
    private AuthService authService;


    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ModelAndView register( CreateAccountDto createAccountDto, RedirectAttributes redirectAttributes){
        boolean valid = service.isEmailInUse(createAccountDto.getEmail());
        ModelAndView model = new ModelAndView();

        if(valid){
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

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ModelAndView  login(AuthRequest authRequest){
        ModelAndView model = new ModelAndView();
        ResponseEntity response = authService.loginUserService(authRequest);
        if(response.getStatusCode() == HttpStatus.UNAUTHORIZED){
            model.addObject("error",true);
            model.setViewName("login");
        }
        else{
            model.setViewName("redirect:/");
        }
        return model;
    }

}
