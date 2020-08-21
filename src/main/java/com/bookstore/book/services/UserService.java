package com.bookstore.book.services;

import com.bookstore.book.dto.CreateAccountDto;
import com.bookstore.book.entities.Account;

public interface UserService {
    Account saveAccount(CreateAccountDto createAccountDto);
}
