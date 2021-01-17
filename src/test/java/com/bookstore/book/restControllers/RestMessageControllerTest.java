package com.bookstore.book.restControllers;

import com.bookstore.book.dto.MessageDto;
import com.bookstore.book.services.MessageService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@WebAppConfiguration
public class RestMessageControllerTest {
    @MockBean
    private MessageService messageService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(roles = {"ADMIN", "USER"})
    @Test
    public void DeleteMessageTest()throws Exception{
        //arrange
        Mockito.doNothing().when(messageService).deleteMessage(Mockito.anyInt());

        //act
        //assert
        mvc.perform(delete("/message-rest/delete-message/{messageId}",10)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @WithMockUser(roles = {"ADMIN", "USER"})
    @Test
    public void ReadMessageTest()throws Exception{
        //arrange
        Mockito.doNothing().when(messageService).markMessageAsRead(Mockito.anyInt());

        //act
        //assert
        mvc.perform(post("/message-rest/{messageId}",10)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @WithMockUser(roles = {"ADMIN", "USER"})
    @Test
    public void ReadAllMessageTest()throws Exception{
        //arrange
        int accountId = 10;
        Gson gson = new Gson();
        String json = gson.toJson(accountId);
        Mockito.doNothing().when(messageService).markMessageAsRead(Mockito.anyInt());

        //act
        //assert
        mvc.perform(post("/message-rest/read-all",10)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andReturn();
    }
    @WithMockUser(roles = {"ADMIN", "USER"})
    @Test
    public void GetAllMessagesTest()throws Exception{
        //arrange
        List<MessageDto> messageDtoList = new ArrayList<MessageDto>();
        when(messageService.getMessages(10)).thenReturn(messageDtoList);

        //act
        //assert
        mvc.perform(get("/message-rest/account-messages/{accountId}",10)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
}
