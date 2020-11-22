package com.bookstore.book.controllers.RestControllers;

import com.bookstore.book.dto.AccountDto;
import com.bookstore.book.dto.CreateAccountDto;
import com.bookstore.book.entities.Account;
import com.bookstore.book.services.AccountService;
import com.bookstore.book.services.AuthService;
import com.bookstore.book.utils.security.requests.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
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
        try {
            boolean valid = service.isEmailInUse(accountDto.getEmail());
            if (valid) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("RZAU001");
            } else {
                Account account = service.saveAccount(accountDto);
                return authService.authorizeNewUser(account, accountDto.getPassword());
            }
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("RZN001");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @PostMapping("/profile-edit-request/{accountId}")
    public ResponseEntity ProfileEditRequest(@PathVariable int accountId, @RequestBody String email) {
        try {
            String response = service.sendConfirmationCodeEmail(accountId, email);
            if (response.equals("Conflict")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("RZAU001");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("RZN001");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @PostMapping("/profile-edit")
    public ResponseEntity ProfileEdit(@RequestBody AccountDto account){
        service.updateProfile(account);
        return ResponseEntity.status(HttpStatus.OK).body("RZDR000");
    }
}
