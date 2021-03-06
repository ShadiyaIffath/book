package com.bookstore.book.controllers.RestControllers;

import com.bookstore.book.dto.BookDtoForAndroid;
import com.bookstore.book.dto.CreateBookDto;
import com.bookstore.book.dto.CreateInquiryDto;
import com.bookstore.book.dto.InquiryDto;
import com.bookstore.book.services.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("inquiry-rest")
public class RestInquiryController {
    @Autowired
    private InquiryService inquiryService;

    @PostMapping("add-inquiry")
    public ResponseEntity AddInquiry(@RequestBody CreateInquiryDto inquiryDto){
        inquiryService.saveInquiry(inquiryDto);
        return ResponseEntity.status(HttpStatus.OK).body("RZS000");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("delete-inquiry/{inquiryId}")
    public ResponseEntity DeleteInquiry(@PathVariable("inquiryId") int inquiryId){
        inquiryService.deleteInquiryById(inquiryId);
        return ResponseEntity.status(HttpStatus.OK).body("RZDR000");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("all-inquiries")
    public List<InquiryDto> GetAllInquiries(){
        return inquiryService.getAllInquiries();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("respond-inquiry/{inquiryId}")
    public ResponseEntity respondInquiry(@PathVariable("inquiryId") int id, @RequestBody String response){
        inquiryService.sendResponseEmail(id, response);
        return ResponseEntity.status(HttpStatus.OK).body("RZDR000");
    }

}
