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

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String showLogin(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", true);

        if (logout != null)
            model.addAttribute("logout", true);

        return "login";
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showRegistration(Model model) {
        model.addAttribute("accountForm", new CreateAccountDto());
        return "register";
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String welcome(@RequestParam(value = "name", defaultValue = "World",
            required = true) String name, Model model){
        model.addAttribute("name", name);
        return "index"; //name of index.jsp
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ModelAndView register( CreateAccountDto createAccountDto, RedirectAttributes redirectAttributes){
        System.out.println(createAccountDto.toString());
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
}
