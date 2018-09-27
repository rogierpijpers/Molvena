package com.capgemini.web;

import org.junit.Test;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetReservationsForGuest() throws Exception{

//        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("Hello World")));

        //TODO: get all reservations for a logged in guest
    }

    @Test
    public void testAddReservationAsGuest(){
        //TODO: create new reservation as guest
    }

    @Test
    public void testGetReservationsAsReceptionist(){
        //TODO: get all reservations when logged in as a receptionist
    }

    @Test
    public void testAddReservationAsReceptionist(){
        //TODO: create new reservation as receptionist for a guest
    }

    @Test
    public void testUpdateReservationAsReceptionist(){
        //TODO: update a reservation when logged in as receptionist
    }

    @Test
    public void testUpdateReservationAsGuest(){
        //TODO: update reservation as a guest !!SHOULD FAIL
    }

    @Test
    public void testDeleteReservationAsReceptionist(){
        //TODO: delete as receptionist
    }

    @Test
    public void testDeleteReceptionistAsGuest(){
        //TODO: delete as guest
    }
}
