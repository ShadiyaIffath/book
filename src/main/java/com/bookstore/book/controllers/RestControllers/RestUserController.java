package com.bookstore.book.controllers.RestControllers;

import com.bookstore.book.dto.CreateAccountDto;
import com.bookstore.book.services.AccountService;
import com.bookstore.book.services.AuthService;
import com.bookstore.book.utils.security.requests.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestUserController {
    @Autowired
    private AccountService service;

    @Autowired
    private AuthService authService;

    @PostMapping("/signIn")
    public ResponseEntity Login(@RequestBody AuthRequest authRequest) {
        return authService.loginUserService(authRequest);
    }

    @PostMapping("/signUp")
    public ResponseEntity Register(@RequestBody CreateAccountDto accountDto){
        boolean valid = service.isEmailInUse(accountDto.getEmail());
        if(!valid){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("RZE1");
        }else{
            return ResponseEntity.ok(service.saveAccount(accountDto));
        }
    }

}
