package com.bookstore.book.controllers;

import com.bookstore.book.dto.CreateAccountDto;
import com.bookstore.book.entities.Account;
import com.bookstore.book.repositories.AccountRepository;
import com.bookstore.book.services.AccountService;
import com.bookstore.book.services.AuthService;
import com.bookstore.book.utils.security.cookieutil.CookieUtil;
import com.bookstore.book.utils.security.jwt.JwtUtils;
import com.bookstore.book.utils.security.payload.UserDetailsImpl;
import com.bookstore.book.utils.security.requests.AuthRequest;

import com.bookstore.book.utils.security.responses.JwtResponse;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    private AccountService service;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(CreateAccountDto createAccountDto, RedirectAttributes redirectAttributes) {
        boolean valid = service.isEmailInUse(createAccountDto.getEmail());
        ModelAndView model = new ModelAndView();

        if (valid) {
            model.addObject("accountForm", createAccountDto);
            model.addObject("emailError", true);
            model.setViewName("register");
        } else {
            service.saveAccount(createAccountDto);
            redirectAttributes.addFlashAttribute("register", true);
            model.setViewName("redirect:login");
        }
        return model;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout() {
        ModelAndView model = new ModelAndView();
        model.addObject("logout", true);
        model.setViewName("redirect:login");
        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(AuthRequest authRequest, HttpServletResponse httpServletResponse
            , HttpServletRequest httpServletRequest) {
        ModelAndView model = new ModelAndView();
        System.out.println(authRequest.getEmail() + " " + authRequest.getPassword());
        if (!authService.validateCredentials(authRequest)) {
            model.addObject("error", true);
            model.setViewName("login");
        } else {
            authService.loginWebPortal(authRequest, httpServletResponse, httpServletRequest);
            model.setViewName("redirect:/");
        }
        return model;

    }

}
