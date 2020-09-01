package com.bookstore.book.services;

import com.bookstore.book.entities.Account;
import com.bookstore.book.repositories.AccountRepository;
import com.bookstore.book.utils.security.jwt.JwtUtils;
import com.bookstore.book.utils.security.payload.UserDetailsImpl;
import com.bookstore.book.utils.security.requests.AuthRequest;
import com.bookstore.book.utils.security.responses.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;

    public ResponseEntity<?> loginUserService(AuthRequest authRequest) {
        System.out.println(authRequest.getPassword());
        Account account = accountRepository.findByEmail(authRequest.getEmail());
        System.out.println(passwordEncoder.encode(authRequest.getPassword()));
        if (account == null || !passwordEncoder.matches(authRequest.getPassword(), account.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Email or password incorrect");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken((authentication));

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }
}
