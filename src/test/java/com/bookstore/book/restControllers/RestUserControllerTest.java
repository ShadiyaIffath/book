package com.bookstore.book.restControllers;

import com.bookstore.book.controllers.RestControllers.RestUserController;
import com.bookstore.book.dto.AccountDto;
import com.bookstore.book.dto.CreateAccountDto;
import com.bookstore.book.entities.Account;
import com.bookstore.book.services.AccountService;
import com.bookstore.book.services.AuthService;
import com.bookstore.book.services.MailService;
import com.bookstore.book.utils.security.requests.AuthRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@WebAppConfiguration
public class RestUserControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService service;

    @MockBean
    private AuthService authService;

    @MockBean
    private MailService mailService;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }


    @WithMockUser(roles = {"USER","ADMIN"})
    @Test
    public void ProfileEditIntegrationTest() throws Exception {
        //arrange
        AccountDto dto = new AccountDto(136,"Shadiya","Iffath","1234","shadiya.iffath98@gmail.com",119,"ROLE_USER","",true);
        when(service.findLoggedInAccount()).thenReturn(new Account());

        Gson gson = new Gson();
        String json = gson.toJson(dto);
        //act
        //assert
        mvc.perform(patch("/profile-edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @WithMockUser(roles = {"USER","ADMIN"})
    @Test
    public void ProfileEditRequest_IntegrationTest_EmailConflict() throws Exception{
        String email = "shadiya.iffath98@gmail.com";
        Gson gson = new Gson();
        String json = gson.toJson(email);
        when(service.verifyEmail(10,email)).thenReturn(false);
        //act
        //assert
        mvc.perform(post("/profile-edit-request/{accountId}",10)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isConflict())
                .andReturn();
    }
    @WithMockUser(roles = {"USER","ADMIN"})
    @Test
    public void ProfileEditRequest_IntegrationTest_Success()throws Exception{
        String email = "shadiya.iffath98@gmail.com";
        Gson gson = new Gson();
        String json = gson.toJson(email);

        when(service.verifyEmail(10,email)).thenReturn(false);
        when(mailService.sendProfileUpdateRequestMail(10)).thenReturn("");

        //act
        //assert
        mvc.perform(post("/profile-edit-request/{accountId}",10)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isConflict())
                .andReturn();
    }

    @Test
    public void SignInTest_Pass()throws Exception{
        AuthRequest request = new AuthRequest("shadiya.iffath98@gmail.com","1234");
        when(authService.loginUserService(request)).thenReturn( new ResponseEntity<>(HttpStatus.OK));

        Gson gson = new Gson();
        String json = gson.toJson(request);
        //act
        //assert
        mvc.perform(post("/signIn")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    public void SignInTest_Fail()throws Exception{
        AuthRequest request = new AuthRequest("shadiya.iffath98@gmail.com","");
        when(authService.loginUserService(request)).thenReturn( new ResponseEntity<>(HttpStatus.UNAUTHORIZED));

        Gson gson = new Gson();
        String json = gson.toJson(request);
        //act
        //assert
        mvc.perform(post("/signIn")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    public void SignUpTest_EmailConflict()throws Exception{
        CreateAccountDto accountDto = new CreateAccountDto("Ela","Bridge","elabridge@gmail.com",
                011111111,"1234",true);
        when(service.isEmailInUse("elabridge@gmail.com")).thenReturn(true);

        Gson gson = new Gson();
        String json = gson.toJson(accountDto);
        //act
        //assert
        mvc.perform(post("/signUp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isConflict())
                .andReturn();
    }

    @Test
    public void SignUpTest_Register()throws Exception{
        CreateAccountDto accountDto = new CreateAccountDto("Ela","Bridge","elabridge@gmail.com",
                011111111,"1234",true);
        when(service.isEmailInUse("elabridge@gmail.com")).thenReturn(false);
        when(service.saveAccount(accountDto)).thenReturn(any(Account.class));
        when(authService.authorizeNewUser(any(Account.class),"1234")).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        Gson gson = new Gson();
        String json = gson.toJson(accountDto);
        //act
        //assert
        mvc.perform(post("/signUp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andReturn();
    }
}
