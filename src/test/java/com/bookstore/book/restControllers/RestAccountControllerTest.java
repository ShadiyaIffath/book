package com.bookstore.book.restControllers;

import com.bookstore.book.dto.AccountDto;
import com.bookstore.book.services.AccountService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import sun.reflect.annotation.ExceptionProxy;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@WebAppConfiguration
public class RestAccountControllerTest{
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService service;

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
    public void GetAllAccountsTest() throws Exception{
        List<AccountDto> dtos = new ArrayList<>();
        //arrange
        when(service.getAllAccounts()).thenReturn(dtos);

        mvc.perform(get("/accounts-rest/all-accounts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void BanAccountTest() throws Exception{

        boolean ban = false;
        Gson gson = new Gson();
        String json = gson.toJson(ban);

        AccountService spy = Mockito.spy(service);
        Mockito.doNothing().when(spy).banAccountById(136,true);

        //act
        //assert
        mvc.perform(post("/accounts-rest/ban-account/{id}",136)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andReturn();
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void DeleteAccountTest()throws Exception{

        Mockito.doNothing().when(service).deleteAccountById(Mockito.anyInt());

        //act
        //assert
        mvc.perform(delete("/accounts-rest/delete-account/{id}",10)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
}
