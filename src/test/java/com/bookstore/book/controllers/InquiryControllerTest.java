package com.bookstore.book.controllers;

import com.bookstore.book.entities.Inquiry;
import com.bookstore.book.repositories.InquiryRepository;
import com.bookstore.book.services.InquiryService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@WebAppConfiguration
public class InquiryControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private InquiryService inquiryService;
    @Autowired
    private InquiryController inquiryController;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testDeleteInquiry() throws Exception {
        //act
        inquiryController.deleteInquiry(1);
        //assert
        verify(inquiryService, times(1)).deleteInquiryById(1);
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testDeleteInquiryURL_WithAdmin() throws Exception {
        //act
        //assert
        mvc.perform(post("/inquiry/delete")
                .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("../inquiries"));
        verify(inquiryService, times(1)).deleteInquiryById(1);
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testRespondInquiryWhenIdExists() throws Exception {
        //act
        inquiryController.respondInquiry("1", "Test");
        //assert
        verify(inquiryService, times(1)).sendResponseEmail(1,"Test");
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testRespondInquiryURL_WithAdmin() throws Exception {
        //act
        //assert
        mvc.perform(post("/inquiry/respond")
                .param("inquiryId", "1")
                .param("response","Test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("../inquiries"));
        verify(inquiryService, times(1)).sendResponseEmail(1,"Test");
    }
} 
