package com.bookstore.book.services;

import com.bookstore.book.dto.CreateInquiryDto;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.bookstore.book.entities.Inquiry;
import com.bookstore.book.repositories.InquiryRepository;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class InquiryServiceTest {

    @Autowired
    private InquiryService inquiryService;

    @MockBean
    private InquiryRepository inquiryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @MockBean
    private MailService mailService;

    @After
    public void after() throws Exception {
    }

    /**
     * Method: saveInquiry(CreateInquiryDto createInquiryDto)
     */
    @Test
    public void testSaveInquiry() throws Exception {
        //before
        CreateInquiryDto inquiryDto = new CreateInquiryDto("x","x@x.com",12345678, "Testing", false);

        //given
        inquiryService.saveInquiry(inquiryDto);

        //assert
        verify(inquiryRepository, times(1)).save(any(Inquiry.class));
    }

    /**
     * Method: getAllInquiries()
     */
    @Test
    public void testGetAllInquiries() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: deleteInquiryById(int id)
     */
    @Test
    public void testDeleteInquiryById() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: sendResponseEmail(int id, String response)
     */
    @Test
    public void testSendResponseEmail() throws Exception {
//TODO: Test goes here... 
    }


} 
