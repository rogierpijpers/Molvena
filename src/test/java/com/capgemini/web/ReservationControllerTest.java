package com.capgemini.web;

import com.capgemini.domain.Reservation;
import com.capgemini.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReservationService reservationService;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    @WithMockUser(username="Jan@vandijk.nl", roles={"GUEST"})
    public void testGetReservationsForGuest() throws Exception{
        List<Reservation> myReservations = reservationService.getReservationsByUsername("Jan@vandijk.nl");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(df);
        String reservationsJson = objectMapper.writeValueAsString(myReservations);

        this.mockMvc.perform(get("/reservation/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(reservationsJson));
    }

//    @Test
//    @WithMockUser(username="Jan@vandijk.nl", roles={"GUEST"})
//    public void testAddReservationAsGuest(){
//        //TODO: create new reservation as guest
//
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
//        df.setTimeZone(TimeZone.getTimeZone("GMT"));
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        objectMapper.setDateFormat(df);
//        Reservation reservation = new Reservation();
//        reservation.setGuest();
//        String reservationJson = objectMapper.writeValueAsString(reservation);
//
//        this.mockMvc.perform(post("/reservation/")).andDo(print()).andExpect(status().isOk()).andExpect(content().json(reservationJson));
//    }

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
