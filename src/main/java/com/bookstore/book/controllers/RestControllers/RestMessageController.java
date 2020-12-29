package com.bookstore.book.controllers.RestControllers;

import com.bookstore.book.dto.BookDtoForAndroid;
import com.bookstore.book.dto.MessageDto;
import com.bookstore.book.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message-rest")
public class RestMessageController {
    @Autowired
    private MessageService messageService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @DeleteMapping("delete-message/{messageId}")
    public ResponseEntity DeleteMessage(@PathVariable int messageId){
        messageService.deleteMessage(messageId);
        return ResponseEntity.status(HttpStatus.OK).body("RZDR000");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @PostMapping("/{messageId}")
    public ResponseEntity ReadMessage(@PathVariable int messageId){
        messageService.markMessageAsRead(messageId);
        return ResponseEntity.status(HttpStatus.OK).body("RZDR000");
    }
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @PostMapping("read-all")
    public ResponseEntity ReadAllMessages(@RequestBody int accountId){
        messageService.markAllAsRead(accountId);
        return ResponseEntity.status(HttpStatus.OK).body("RZDR000");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @GetMapping("account-messages/{accountId}")
    public List<MessageDto> GetAllAccountMessages(@PathVariable int accountId){
        return messageService.getMessages(accountId);
    }
}
