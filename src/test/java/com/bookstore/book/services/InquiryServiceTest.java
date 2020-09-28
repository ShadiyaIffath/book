package com.bookstore.book.services;

import com.bookstore.book.dto.CreateInquiryDto;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.bookstore.book.dto.InquiryDto;
import com.bookstore.book.entities.Inquiry;
import com.bookstore.book.repositories.InquiryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class InquiryServiceTest {
    @Autowired
    private InquiryService inquiryService;
    @MockBean
    private InquiryRepository inquiryRepository;
    @MockBean
    private MailService mailService;

    @Test
    public void testSaveInquiry() throws Exception {
        //before
        CreateInquiryDto inquiryDto = new CreateInquiryDto("x","x@x.com",
                12345678, "Testing", false);
        //given
        inquiryService.saveInquiry(inquiryDto);
        //assert
        verify(inquiryRepository, times(1)).save(any(Inquiry.class));
    }

    @Test
    public void testGetAllInquiries() throws Exception {
        //before
        Inquiry inquiry = new Inquiry(1,"x","x@x.com",12345678,
                "Testing", false);
        Inquiry inquiry1 = new Inquiry(2,"x","x@x.com",12345678,
                "Testing", false);
        List<Inquiry> inquiryList = new ArrayList<>();
        inquiryList.add(inquiry);
        inquiryList.add(inquiry1);
        when(inquiryRepository.findAll()).thenReturn(inquiryList);
        //given
        List<InquiryDto> inquiries = inquiryService.getAllInquiries();
        //assert
        assertEquals(inquiries.size(),(inquiryList.size()));
    }

    @Test
    public void testDeleteInquiryById() throws Exception {
        //given
       inquiryService.deleteInquiryById(1);
       //assert
       verify(inquiryRepository, times(1)).deleteById(1);
    }

    @Test
    public void testSendResponseEmailWithValidId() throws Exception {
        //before
        Inquiry inquiry = new Inquiry(1,"x","x@x.com",12345678,
                "Testing", false);
        when(inquiryRepository.findInquiriesById(1)).thenReturn(inquiry);
        String response = "answer";
        String inquiryResponse = "Your Question: " + inquiry.getQuestion() + "\n" +
                "Our response: " +response;

        //given
        inquiryService.sendResponseEmail(1, response);

        //assert
        verify(mailService, times(1)).sendMail("Response to your inquiry",
                "x@x.com",inquiryResponse);
        inquiry.setResponded(true);
        verify(inquiryRepository, times(1)).save(inquiry);
    }

    @Test
    public void testSendResponseEmailWithInvalidId() throws Exception{
        //before
        Inquiry inquiry = new Inquiry(1,"x","x@x.com",12345678,
                "Testing", false);
        when(inquiryRepository.findInquiriesById(1)).thenReturn(null);
        String response = "answer";
        String inquiryResponse = "Your Question: " + inquiry.getQuestion() + "\n" +
                "Our response: " +response;
        //given
        inquiryService.sendResponseEmail(1,response);

        //assert
        verify(mailService, times(0)).sendMail("Response to your inquiry",
                "x@x.com",inquiryResponse);
        verify(inquiryRepository, times(0)).save(inquiry);
    }
} 
