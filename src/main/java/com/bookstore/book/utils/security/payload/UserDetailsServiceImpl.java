package com.bookstore.book.utils.security.payload;


import com.bookstore.book.entities.Account;
import com.bookstore.book.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AccountService accountService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = accountService.findByEmail(username);
        return UserDetailsImpl.build(user);
    }
}
