package com.bookstore.book.services;

import com.bookstore.book.dto.CreateAccountDto;
import com.bookstore.book.entities.Account;
import com.bookstore.book.repositories.AccountRepository;
import com.bookstore.book.utils.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AccountService implements UserService {

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

    public Account saveAccount(CreateAccountDto createAccountDto){
        Account account = modelMapper.map(createAccountDto, Account.class);
        account.setType(Role.RXS.getRole());
        account.setPassword(passwordEncoder.encode(createAccountDto.getPassword()));
        return  accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(s);
        System.out.println(account.getFirstName()+ account.getPassword());
        if (account == null) {
            throw new UsernameNotFoundException("Invalid email or password.");
        }
        return new org.springframework.security.core.userdetails.User(account.getEmail(), account.getPassword(),mapRoleToAuthorities(account.getType()));
    }


    private Collection<? extends GrantedAuthority> mapRoleToAuthorities(String role){
        List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
        auths.add(new SimpleGrantedAuthority(role));
        return auths;
    }
}
