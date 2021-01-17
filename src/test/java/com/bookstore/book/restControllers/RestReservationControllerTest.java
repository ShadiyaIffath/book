package com.bookstore.book.restControllers;

import com.bookstore.book.dto.*;
import com.bookstore.book.services.ReservationService;
import com.google.gson.Gson;
import org.assertj.core.api.Assertions;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@WebAppConfiguration
public class RestReservationControllerTest {

    @MockBean
    private ReservationService reservationService;

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
    public void AddReservationTest_EmptyBody()throws Exception{
        //act
        //assert
        mvc.perform(post("/reservation-rest/new-reservation")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @WithMockUser(roles = {"ADMIN", "USER"})
    @Test
    public void AddReservationTest_AccountInvalid()throws Exception{
        //arrange
        CreateReservationDto Dto = new CreateReservationDto("2020-12-12", null,"2020-12-29","Created",new BookDto(),1,1);
        Gson gson = new Gson();
        String json = gson.toJson(Dto);
        when(reservationService.validateAccountValidity(Mockito.anyInt())).thenReturn(false);
        //act
        //assert
        MvcResult mvcResult =mvc.perform(post("/reservation-rest/new-reservation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isConflict())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        Assertions.assertThat(responseBody).isEqualTo("RZAU002");
    }

    @WithMockUser(roles = {"ADMIN", "USER"})
    @Test
    public void AddReservationTest_ReservationConflict()throws Exception{
        //arrange
        CreateReservationDto Dto = new CreateReservationDto("2020-12-12", null,"2020-12-29","Created",new BookDto(),1,1);
        Gson gson = new Gson();
        String json = gson.toJson(Dto);
        when(reservationService.validateAccountValidity(Mockito.anyInt())).thenReturn(true);
        when(reservationService.newReservation(Dto)).thenReturn(false);
        //act
        //assert
        MvcResult mvcResult =mvc.perform(post("/reservation-rest/new-reservation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isConflict())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        Assertions.assertThat(responseBody).isEqualTo("RZAU001");
    }

    @WithMockUser(roles = {"ADMIN", "USER"})
    @Test
    public void AddReservationTest_ReservationSuccessful()throws Exception{
        //arrange
        CreateReservationDto Dto = new CreateReservationDto("2020-12-12", null,"2020-12-29","Created",new BookDto(),1,1);
        Gson gson = new Gson();
        String json = gson.toJson(Dto);
        when(reservationService.validateAccountValidity(Mockito.anyInt())).thenReturn(true);
        when(reservationService.newReservation(Mockito.any(CreateReservationDto.class))).thenReturn(true);
        //act
        //assert
        MvcResult mvcResult =mvc.perform(post("/reservation-rest/new-reservation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        Assertions.assertThat(responseBody).isEqualTo("RZS000");
    }

    @WithMockUser(roles = {"ADMIN", "USER"})
    @Test
    public void DeleteReservationTest()throws Exception{
        //arrange
        Mockito.doNothing().when(reservationService).removeReservation(Mockito.anyInt());

        //act
        //assert
        mvc.perform(delete("/reservation-rest/delete-reservation/{reservationId}",10)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @WithMockUser(roles = {"ADMIN", "USER"})
    @Test
    public void CancelReservationTest()throws Exception{
        //arrange
        Mockito.doNothing().when(reservationService).cancelReservation(Mockito.anyInt());

        //act
        //assert
        mvc.perform(post("/reservation-rest/cancel-reservation/{reservationId}",10)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    public void GetAllReservationsTest()throws Exception{
        //arrange
        List<ReservationDtoForAndroid> dtos = new ArrayList<>();
        when(reservationService.getAllReservationsForAndroid()).thenReturn(dtos);

        //act
        //assert
        mvc.perform(get("/reservation-rest/all-reservations")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @WithMockUser(roles = {"ADMIN", "USER"})
    @Test
    public void GetAllAccountReservationsTest()throws Exception{
        //arrange
        List<ReservationDtoForAndroid> dtos = new ArrayList<>();
        when(reservationService.getAccountReservationsForAndroid(Mockito.anyInt())).thenReturn(dtos);

        //act
        //assert
        mvc.perform(get("/reservation-rest/account-reservations/{accountId}",10)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @WithMockUser(roles = {"ADMIN", "USER"})
    @Test
    public void EditReservationTest_Conflict()throws Exception{
        //arrange
        ReservationDto dto = new ReservationDto(10,"","","","","",new BookDto(),new AccountDto());
        Gson gson = new Gson();
        String json = gson.toJson(dto);
        when(reservationService.updateReservation(dto)).thenReturn(false);

        //act
        //assert
        mvc.perform(patch("/reservation-rest/edit-reservation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isConflict())
                .andReturn();
    }


    @WithMockUser(roles = {"ADMIN", "USER"})
    @Test
    public void EditReservationTest_Success()throws Exception{
        //arrange
        ReservationDto dto = new ReservationDto();
        Gson gson = new Gson();
        String json = gson.toJson(dto);
        when(reservationService.updateReservation(Mockito.any(ReservationDto.class))).thenReturn(true);

        //act
        //assert
        mvc.perform(patch("/reservation-rest/edit-reservation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andReturn();
    }
}
