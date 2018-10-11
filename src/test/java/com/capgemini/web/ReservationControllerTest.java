package com.capgemini.web;

import com.capgemini.TestJpaConfig;
import com.capgemini.data.GuestRepository;
import com.capgemini.data.ReservationRepository;
import com.capgemini.domain.*;
import com.capgemini.service.ReservationService;
import com.capgemini.web.authentication.AuthenticationHelper;
import com.capgemini.web.dto.CancelReservationDTO;
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
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private GuestRepository guestRepository;

    @MockBean
    private ReservationRepository reservationRepository;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ReservationService reservationService;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        MockitoAnnotations.initMocks(this);
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

    private Guest createAlternateDummyGuest(){
        Guest guest = new Guest();
        guest.setFirstName("Tom");
        guest.setLastName("van der Steen");
        guest.setPhone("06-12345678");
        guest.setPassword("$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2");
        guest.setMail("Tom@vandersteen.nl");
        guest.setAddress("Straat 1");
        guest.setState("");
        guest.setZipCode("5555LL");
        guest.setCountry("NL");
        guest.setDateOfBirth(new Date(31-8-1994));
        return guest;
    }

    @Test
    @WithMockUser(username="Jan@vandijk.nl", roles={"GUEST"})
    public void testGetReservationsForGuest() throws Exception{
        Guest guest = createDummyGuest();
        Reservation reservation = createDummyReservation(guest);

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);

        when(reservationRepository.findAll()).thenReturn(reservations);

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
    @WithMockUser(username="Corry@vanvliet.nl", roles={"RECEPTIONIST"})
    public void testAddReservationAsReceptionist() throws Exception {

        List<Reservation> myReservations = reservationService.getReservationsByUsername("Jan@vandijk.nl");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(df);

        Guest guest = createDummyGuest();
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

        when(guestRepository.findByMail("Jan@vandijk.nl")).thenReturn(guest);

        String result = objectMapper.writeValueAsString(reservation);
        this.mockMvc.perform(post("/reservation/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(result))
                .andDo(print())
                .andExpect(status().isOk());

        myReservations.add(reservation);
        String reservationsJson = objectMapper.writeValueAsString(myReservations);

        when(reservationRepository.findAll()).thenReturn(myReservations);

        this.mockMvc.perform(get("/reservation/"))
                .andDo(print())
                .andExpect(content().json(reservationsJson))
                .andExpect(status().isOk());
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

        when(guestRepository.findByMail("Tom@vandersteen.nl")).thenReturn(createAlternateDummyGuest());

        Reservation reservation = new Reservation();
        reservation.setGuest(guestRepository.findByMail("Tom@vandersteen.nl"));
        reservation.setAmountOfGuests(5);
        reservation.setStartDate(new Date());
        reservation.setEndDate(new Date());
        Room room = new Room();
        byte number = 2;
        room.setRoomType(new RoomType(number, number));
        reservation.setRoom(room);

        String result = objectMapper.writeValueAsString(reservation);

        try {
            this.mockMvc.perform(put("/reservation/" + reservation.getReservationID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(result))
                .andDo(print());
            Assert.assertTrue(false);
        } catch (Exception e) {
            Assert.assertTrue(e.getCause() instanceof AccessDeniedException);
        }
    }

    @Test
    @WithMockUser(username="Jan@vandijk.nl", roles={"GUEST"})
    public void testCancelReservationAsGuest() throws Exception {
        List<Reservation> dummyReservations = new ArrayList<>();
        dummyReservations.add(createDummyReservation(createDummyGuest()));
        when(reservationRepository.findAll()).thenReturn(dummyReservations);

        List<Reservation> myReservations = reservationService.getReservationsByUsername("Jan@vandijk.nl");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(df);

        Reservation reservation = myReservations.get(0);

        CancelReservationDTO dto = new CancelReservationDTO();
        dto.setChargeCancellationConditions(false);
        dto.setReservationId(reservation.getReservationID());

        String reservationsJson = objectMapper.writeValueAsString(dto);

        this.mockMvc.perform(put("/reservation/cancel/" + reservation.getReservationID()).contentType(MediaType.APPLICATION_JSON)
                .content(reservationsJson)).andDo(print()).andExpect(status().isOk());

        this.mockMvc.perform(get("/reservation/" + reservation.getReservationID())).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"chargedPercentage\":100.0")));
    }

    @Test
    @WithMockUser(username="Corry@vanvliet.nl", roles={"RECEPTIONIST"})
    public void testCancelReservationAsReceptionistFreeOfCharge() throws Exception {
        List<Reservation> dummyReservations = new ArrayList<>();
        dummyReservations.add(createDummyReservation(createDummyGuest()));
        when(reservationRepository.findAll()).thenReturn(dummyReservations);

        List<Reservation> myReservations = reservationService.getReservationsByUsername("Jan@vandijk.nl");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(df);

        Reservation reservation = myReservations.get(0);

        CancelReservationDTO dto = new CancelReservationDTO();
        dto.setChargeCancellationConditions(false);
        dto.setReservationId(reservation.getReservationID());

        String reservationsJson = objectMapper.writeValueAsString(dto);

        this.mockMvc.perform(put("/reservation/cancel/" + reservation.getReservationID()).contentType(MediaType.APPLICATION_JSON)
                .content(reservationsJson)).andDo(print()).andExpect(status().isOk());

        this.mockMvc.perform(get("/reservation/" + reservation.getReservationID())).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"chargedPercentage\":0.0")));
    }
}
