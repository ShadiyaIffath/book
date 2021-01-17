package com.bookstore.book.restControllers;

import com.bookstore.book.dto.CreateInquiryDto;
import com.bookstore.book.dto.InquiryDto;
import com.bookstore.book.services.InquiryService;
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
public class RestInquiryControllerTest {
    @MockBean
    private InquiryService inquiryService;

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
    @Test
    public void AddInquiryTest()throws Exception{
        //arrange
        CreateInquiryDto inquiryDto = new CreateInquiryDto("","",4564646,"",false);
        Mockito.doNothing().when(inquiryService).saveInquiry(inquiryDto);
        Gson gson = new Gson();
        String json = gson.toJson(inquiryDto);

        //act
        //assert
        mvc.perform(post("/inquiry-rest/add-inquiry")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andReturn();
    }
    @WithMockUser(roles = {"ADMIN"})
    @Test
    public void DeleteInquiryTest()throws Exception{
        //arrange
        Mockito.doNothing().when(inquiryService).deleteInquiryById(Mockito.anyInt());
        //act
        //assert
        mvc.perform(delete("/inquiry-rest/delete-inquiry/{inquiryId}",10)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
    @WithMockUser(roles = {"ADMIN"})
    @Test
    public void GetAllInquiriesTest()throws Exception{
        //arrange
        List<InquiryDto> inquiryDtoList = new ArrayList<>();
        when(inquiryService.getAllInquiries()).thenReturn(inquiryDtoList);

        //act
        //assert
        mvc.perform(get("/inquiry-rest/all-inquiries")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    public void RespondToInquiryTest()throws Exception{
        //arrange
        Mockito.doNothing().when(inquiryService).sendResponseEmail(10,"TEST");
        String response = "TEST";
        Gson gson = new Gson();
        String json = gson.toJson(response);

        //act
        //assert
        mvc.perform(post("/inquiry-rest/respond-inquiry/{inquiryId}",10)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andReturn();
    }
}
