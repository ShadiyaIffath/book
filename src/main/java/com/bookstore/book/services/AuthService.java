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
import org.springframework.security.core.GrantedAuthority;
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

    //mobile application
    public ResponseEntity<?> loginUserService(AuthRequest authRequest) {
        Account account = accountRepository.findByEmail(authRequest.getEmail());
        if (account == null || !passwordEncoder.matches(authRequest.getPassword(), account.getPassword()) || !account.getActive()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("RZAU000");
        }

        return getResponseEntity(account, authRequest.getEmail(), authRequest.getPassword());
    }

    public void loginWebPortal(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public ResponseEntity authorizeNewUser(Account account, String password){
            return getResponseEntity(account, account.getEmail(), password);
    }

    private ResponseEntity getResponseEntity(Account account, String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken((authentication));
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                account.getFirstName(),
                account.getLastName(),
                roles, account.getPhone(), account.getType()));
    }

    public boolean validateCredentials(AuthRequest authRequest) {
        boolean valid = true;
        Account account = accountRepository.findByEmail(authRequest.getEmail());
        if (account == null || !passwordEncoder.matches(authRequest.getPassword(), account.getPassword()) || !account.getActive())
            valid = false;

        return valid;
    }
}
