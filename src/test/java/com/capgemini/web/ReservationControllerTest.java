package com.capgemini.web;

import com.capgemini.data.GuestRepository;
import com.capgemini.domain.Guest;
import com.capgemini.domain.Reservation;
import com.capgemini.domain.Room;
import com.capgemini.domain.RoomType;
import com.capgemini.resources.H2TestProfileJPAConfig;
import com.capgemini.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ReservationControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private GuestRepository guestRepository;


    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    private Reservation createDummyReservation(Guest guest){
        Date startDate = new Date();
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(startDate);
        cal.add(Calendar.DATE, 1);
        Date endDate = cal.getTime();

        Room room = new Room();
        room.setRoomID((short) 0);
        room.setRoomType(new RoomType((byte)2, (byte)2));

        Reservation myReservation = new Reservation(startDate, endDate, (Guest) guest, 6, room, room.getRoomType());
        return myReservation;
    }

    private Guest createDummyGuest(){
        Guest guest = new Guest();
        guest.setFirstName("Jan");
        guest.setLastName("van Dijk");
        guest.setPhone("123456789");
        guest.setPassword("$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2");
        guest.setMail("Jan@vandijk.nl");
        guest.setAddress("Straat 1");
        guest.setZipCode("5555LL");
        guest.setCountry("NL");
        guest.setDateOfBirth(new Date(31-8-1994));
        return guest;
    }

    @Test
    @WithMockUser(username="Jan@vandijk.nl", roles={"GUEST"})
    public void testGetReservationsForGuest() throws Exception{
        Guest guest = createDummyGuest();
        guestRepository.save(guest);

        Reservation reservation = createDummyReservation(guest);
        reservationService.addReservation(reservation);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(df);
        String reservationsJson = objectMapper.writeValueAsString(reservationService.getReservationsByUsername("Jan@vandijk.nl"));

        this.mockMvc.perform(get("/reservation/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(reservationsJson));
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
