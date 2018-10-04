package com.capgemini.web;

import com.capgemini.data.GuestRepository;
import com.capgemini.data.ReservationRepository;
import com.capgemini.domain.Guest;
import com.capgemini.domain.Reservation;
import com.capgemini.domain.Room;
import com.capgemini.domain.RoomType;
import com.capgemini.service.ReservationService;
import com.capgemini.web.authentication.AuthenticationHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.constraints.Max;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private GuestRepository guestRepository;

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
//    @WithMockUser(username="Corry@vanvliet.nl", roles={"RECEPTIONIST"})
//    public void testGetReservationsAsReceptionist ()throws Exception{
//        List<Reservation> myReservations = reservationService.getReservationsByUsername("Jan@vandijk.nl");
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
//        df.setTimeZone(TimeZone.getTimeZone("GMT"));
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        objectMapper.setDateFormat(df);
//        String reservationsJson = objectMapper.writeValueAsString(myReservations);
//        this.mockMvc.perform(get("/reservation/")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(reservationsJson));
//    }

    @Test
    @WithMockUser(username="Corry@vanvliet.nl", roles={"RECEPTIONIST"})
    public void testAddReservationAsReceptionist() throws Exception {

        List<Reservation> myReservations = reservationService.getReservationsByUsername("Jan@vandijk.nl");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(df);

        Guest guest = guestRepository.getGuestByUsername("Jan@vandijk.nl");
        Reservation reservation = new Reservation();
        reservation.setGuest(guest);
        reservation.setAmountOfGuests(5);
        reservation.setStartDate(new Date());
        reservation.setEndDate(new Date());
        Room room = new Room();
        byte number = 2;
        room.setRoomType(new RoomType(number, number));
        reservation.setRoom(room);
        reservation.setReservationID(1);

        String result = objectMapper.writeValueAsString(reservation);
        this.mockMvc.perform(post("/reservation/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(result))
                .andDo(print())
                .andExpect(status().isOk());

        myReservations.add(reservation);
        String reservationsJson = objectMapper.writeValueAsString(myReservations);


        this.mockMvc.perform(get("/reservation/"))
                .andDo(print())
                .andExpect(content().json(reservationsJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateReservationAsReceptionist(){
        //TODO: update a reservation when logged in as receptionist
    }

    @Test
    @WithMockUser(username="Jan@vandijk.nl", roles={"GUEST"})
    public void testUpdateReservationAsGuest() throws Exception{
        //TODO: update reservation as a guest !!SHOULD FAIL

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(df);

        Reservation reservation = new Reservation();
        reservation.setGuest(guestRepository.getGuestByUsername("Corry@vanvliet.nl"));
        reservation.setAmountOfGuests(5);
        reservation.setStartDate(new Date());
        reservation.setEndDate(new Date());
        Room room = new Room();
        byte number = 2;
        room.setRoomType(new RoomType(number, number));
        reservation.setRoom(room);

        String result = objectMapper.writeValueAsString(reservation);

        try {
        this.mockMvc.perform(put("/reservation/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(result))
                .andDo(print());
        } catch (Exception e) {
            Assert.assertTrue(e.getCause() instanceof AccessDeniedException);
        }
    }
}
