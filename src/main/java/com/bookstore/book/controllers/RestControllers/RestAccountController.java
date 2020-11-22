package com.bookstore.book.controllers.RestControllers;

import com.bookstore.book.dto.AccountDto;
import com.bookstore.book.dto.BookDtoForAndroid;
import com.bookstore.book.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("accounts-rest")
public class RestAccountController {
    @Autowired
    private AccountService accountService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("all-accounts")
    public List<AccountDto> GetAllAccounts(){
        return accountService.getAllAccounts();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("ban-account/{id}")
    public ResponseEntity BanAccount(@PathVariable("id") int id, @RequestBody boolean ban) {
        accountService.banAccountById(id, !ban);
        return ResponseEntity.status(HttpStatus.OK).body("RZDR000");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("delete-account")
    public ResponseEntity DeleteAccount(@RequestBody int id) {
        accountService.deleteAccountById(id);
        return ResponseEntity.status(HttpStatus.OK).body("RZDR000");
    }

}
