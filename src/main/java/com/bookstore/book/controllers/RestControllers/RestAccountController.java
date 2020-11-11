package com.bookstore.book.controllers.RestControllers;

import com.bookstore.book.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAccountController {
    @Autowired
    private AccountService accountService;

}
