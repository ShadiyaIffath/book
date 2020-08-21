package com.bookstore.book.services;

import com.bookstore.book.dto.CreateAccountDto;
import com.bookstore.book.entities.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Account saveAccount(CreateAccountDto createAccountDto);
}
