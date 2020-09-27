package com.bookstore.book.controllers;

import com.bookstore.book.controllers.UserController;
import com.bookstore.book.dto.CreateAccountDto;
import com.bookstore.book.services.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AccountService service;
    /**
* 
* Method: register(CreateAccountDto createAccountDto, RedirectAttributes redirectAttributes) 
* 
*/ 
@Test
public void test() throws Exception {
    CreateAccountDto dto = new CreateAccountDto("Ela","Bridge","elabridge@gmail.com",011111111,"1234",true);
    mockMvc.perform(post("/register")
            .contentType(MediaType.ALL)
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk());
} 

/** 
* 
* Method: logout(HttpServletResponse response) 
* 
*/ 
@Test
public void testLogout() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: login(AuthRequest authRequest, HttpServletResponse httpServletResponse) 
* 
*/ 
@Test
public void testLogin() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: editProfile(AccountDto accountDto) 
* 
*/ 
@Test
public void testEditProfile() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: contactUs(CreateInquiryDto dto) 
* 
*/ 
@Test
public void testContactUs() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: deleteMessage(@RequestParam("id") int id) 
* 
*/ 
@Test
public void testDeleteMessage() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: readMessage(@PathVariable int id) 
* 
*/ 
@Test
public void testReadMessage() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: readAllMessages() 
* 
*/ 
@Test
public void testReadAllMessages() throws Exception { 
//TODO: Test goes here... 
} 


} 
