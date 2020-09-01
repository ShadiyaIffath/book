package com.bookstore.book.services;

import com.bookstore.book.dto.CreateAccountDto;
import com.bookstore.book.entities.Account;
import com.bookstore.book.repositories.AccountRepository;
import com.bookstore.book.utils.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean isEmailInUse(String email){
        boolean valid = false;
        if(accountRepository.findByEmail(email) != null)
            valid = true;
        return valid;
    }

    public Account findByEmail(String email){
        return accountRepository.findByEmail(email);
    }

    public Account saveAccount(CreateAccountDto createAccountDto){
        Account account = modelMapper.map(createAccountDto, Account.class);
        account.setType(Role.RXS.getRole());
        account.setPassword(passwordEncoder.encode(createAccountDto.getPassword()));
        return  accountRepository.save(account);
    }

}
