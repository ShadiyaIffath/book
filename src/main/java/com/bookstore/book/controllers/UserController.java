package com.bookstore.book.controllers;

import com.bookstore.book.dto.AccountDto;
import com.bookstore.book.dto.CreateAccountDto;
import com.bookstore.book.dto.CreateInquiryDto;
import com.bookstore.book.services.AccountService;
import com.bookstore.book.services.AuthService;
import com.bookstore.book.services.InquiryService;
import com.bookstore.book.utils.security.requests.AuthRequest;
import com.bookstore.book.utils.security.responses.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    private AccountService service;

    @Autowired
    private AuthService authService;

    @Autowired
    private InquiryService inquiryService;

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

    @PreAuthorize("hasAnyRole()")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        model.addObject("logout", true);
        model.setViewName("redirect:login");
        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(AuthRequest authRequest, HttpServletResponse httpServletResponse) {
        ModelAndView model = new ModelAndView();
        if (!authService.validateCredentials(authRequest)) {
            model.addObject("error", true);
            model.setViewName("login");
        } else {
            JwtResponse response = authService.loginWebPortal(authRequest);
            httpServletResponse.addHeader("Authorization",response.getToken());
            model.setViewName("redirect:/");
        }
        return model;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @RequestMapping(value="/profile", method = RequestMethod.POST)
    public ModelAndView editProfile(AccountDto accountDto){
        ModelAndView model = new ModelAndView("redirect:profile");
        model.addObject("accounts", service.updateAccount(accountDto));
        return model;
    }

    @RequestMapping(value="/contact", method=RequestMethod.POST)
    public ModelAndView contactUs(CreateInquiryDto dto){
        inquiryService.saveInquiry(dto);
        return new ModelAndView("redirect:/");
    }

}
