package com.bookstore.book.services;

import com.bookstore.book.dto.AccountDto;
import com.bookstore.book.dto.CreateAccountDto;
import com.bookstore.book.entities.Account;
import com.bookstore.book.repositories.AccountRepository;
import com.bookstore.book.utils.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean isEmailInUse(String email) {
        boolean valid = false;
        if (accountRepository.findByEmail(email) != null)
            valid = true;
        return valid;
    }

    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public Account saveAccount(CreateAccountDto createAccountDto) {
        Account account = modelMapper.map(createAccountDto, Account.class);
        account.setType(Role.RXS.getRole());
        account.setPassword(passwordEncoder.encode(createAccountDto.getPassword()));
        return accountRepository.save(account);
    }

    public AccountDto updateAccount(AccountDto accountDto){
        Account account = findLoggedInAccount();
        account.setFirstName(accountDto.getFirstName());
        account.setLastName(accountDto.getLastName());
        account.setPhone(accountDto.getPhone());
        accountRepository.save(account);
        accountDto = modelMapper.map(accountDto, AccountDto.class);
        return accountDto;
    }

    public Account findLoggedInAccount() {
        String email = findLoggedInAccountEmail();
        return accountRepository.findByEmail(email);
    }

    public AccountDto getLoggedInAccount(){
        Account account = findLoggedInAccount();
        return modelMapper.map(account, AccountDto.class);
    }

    public String findLoggedInAccountEmail(){
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

    public List<AccountDto> getAllAccounts() {
        List<AccountDto> accounts = accountRepository.findAll()
                .stream().map(x -> {
                    return modelMapper.map(x, AccountDto.class);
                })
                .collect(Collectors.toList());
        return accounts;
    }

    public boolean verifyAccount(String email){
        boolean valid = false;
        if(email.equals(findLoggedInAccountEmail()))
            valid = true;
        return valid;
    }

    public void deleteAccountById(int id){ accountRepository.deleteById(id);}

    @Transactional
    public void banAccountById(int id, boolean ban){ accountRepository.banAccountById(id,ban);}

}
