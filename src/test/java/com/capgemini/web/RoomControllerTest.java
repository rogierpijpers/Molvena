package com.capgemini.web;

import com.capgemini.data.ReservationRepository;
import com.capgemini.data.RoomRepository;
import com.capgemini.data.RoomTypeRepository;
import com.capgemini.domain.Guest;
import com.capgemini.domain.Reservation;
import com.capgemini.domain.Room;
import com.capgemini.domain.RoomType;
import com.capgemini.service.ReservationService;
import com.capgemini.service.RoomService;
import com.capgemini.service.RoomTypeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.capgemini.web.util.exception.InvalidInputException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RoomControllerTest {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private RoomService roomService;

    @MockBean
    private RoomTypeRepository roomTypeRepository;

    @MockBean
    private ReservationRepository reservationRepository;

    @MockBean
    private RoomRepository roomRepository;

    @InjectMocks
    private ReservationService reservationService;

    @InjectMocks
    private RoomTypeService roomTypeService;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        MockitoAnnotations.initMocks(this);
    }

    private List<RoomType> getDummyRoomTypes() {
        List<RoomType> roomTypes = new ArrayList<>();
        roomTypes.add(new RoomType((byte) 1, (byte) 1));
        roomTypes.add(new RoomType((byte) 2, (byte) 0));
        roomTypes.add(new RoomType((byte) 3, (byte) 0));
        roomTypes.add(new RoomType((byte) 4, (byte) 0));
        roomTypes.add(new RoomType((byte) 0, (byte) 1));
        roomTypes.add(new RoomType((byte) 0, (byte) 2));
        return roomTypes;
    }

    private Room createDummyRoom(RoomType roomType){
        Room room = new Room();
        room.setRoomType(roomType);
        return room;
    }

    private Reservation createDummyReservation(Guest guest){
        Date startDate = new Date();
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(startDate);
        cal.add(Calendar.DATE, 1);
        Date endDate = cal.getTime();

        Room room = createDummyRoom(getDummyRoomTypes().get(0));

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
    @WithMockUser(username="Henk@vanvliet.nl", roles={"ADMIN"})
    public void testGetAmountOfAvailableRoomsForRoomType() throws Exception {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(createDummyReservation(createDummyGuest()));
        List<Room> rooms = new ArrayList<>();
        rooms.add(createDummyRoom(getDummyRoomTypes().get(0)));

        when(roomTypeRepository.findById((long) 0)).thenReturn(java.util.Optional.ofNullable(getDummyRoomTypes().get(0)));
        when(roomTypeRepository.findAll()).thenReturn(getDummyRoomTypes());
        when(reservationRepository.findAll()).thenReturn(reservations);
        when(roomRepository.findAll()).thenReturn(rooms);

        RoomType roomType = roomTypeService.getRoomTypeById(0);

        Date startDate = new Date(2017-10-20);
        Date endDate = new Date(2017-10-22);

        long expectedAmount = reservationService.getAllAvailableRooms(startDate, endDate, roomType).stream().filter(x -> x.getRoomType().equals(roomType)).count();
        Assert.assertTrue(expectedAmount > 0);

        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        String isoStartDate = df.format(startDate);
        String isoEndDate = df.format(endDate);

        this.mockMvc.perform(get("/roomtype/available/" + isoStartDate + "/" + isoEndDate + "/0"))
                .andDo(print()).andExpect(status().isOk()).andExpect(content().json(Long.toString(expectedAmount)));
    }

    @Test
    @WithMockUser(username="Henk@vanvliet.nl", roles={"ADMIN"})
    public void testGetAvailableRoomsWithRoomTypeFilter() throws Exception {
        RoomType roomType = roomTypeService.getRoomTypeById(0);

        Date startDate = new Date(2017-10-20);
        Date endDate = new Date(2017-10-22);

        List<Room> availableRooms = reservationService.getAllAvailableRooms(startDate, endDate, roomType);
        Assert.assertTrue(availableRooms.size() > 0);

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writeValueAsString(availableRooms);

        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        String isoStartDate = df.format(startDate);
        String isoEndDate = df.format(endDate);

        this.mockMvc.perform(get("/room/available/" + isoStartDate + "/" + isoEndDate + "/0")).andDo(print())
                .andExpect(status().isOk()).andExpect(content().json(expectedJson));

    }

    @Test
    @WithMockUser(username="Henk@vanvliet.nl", roles={"ADMIN"})
    public void testDeleteRoom() throws Exception {
        Room room = roomService.getRoomById((short) 0);
        if(room == null){
            room = new Room();
            room.setRoomID((short) 0);
            room.setRoomType(new RoomType((byte) 2, (byte) 2));
            roomService.createRoom(room);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonExpected = objectMapper.writeValueAsString(room);

        this.mockMvc.perform(get("/room/0")).andDo(print()).andExpect(status().isOk()).andExpect(content().json(jsonExpected));
        this.mockMvc.perform(delete("/room/0")).andDo(print()).andExpect(status().isOk());
        this.mockMvc.perform(get("/room/0")).andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username="Henk@vanvliet.nl", roles={"ADMIN"})
    public void testCreateRoom() throws Exception {
        Room room = new Room();
        room.setRoomID((short) 5432);
        room.setRoomType(new RoomType((byte) 2, (byte) 2));

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonExpected = objectMapper.writeValueAsString(room);

        this.mockMvc.perform(post("/room/").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonExpected)).andDo(print()).andExpect(status().isOk());
        this.mockMvc.perform(get("/room/5432")).andDo(print()).andExpect(status().isOk()).andExpect(content().json(jsonExpected));
    }

    @Test
    @WithMockUser(username="Henk@vanvliet.nl", roles={"ADMIN"})
    public void testUpdateRoom() throws Exception{
        Room room = roomService.getAllRooms().get(0);
        Assert.assertTrue(room.getRoomID() == 0);

        Room newRoom = new Room();
        newRoom.setRoomID((short) 99);
        newRoom.setRoomType(room.getRoomType());

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonExpected = objectMapper.writeValueAsString(newRoom);

        this.mockMvc.perform(put("/room/0").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonExpected)).andDo(print()).andExpect(status().isOk());

        this.mockMvc.perform(get("/room/99")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(jsonExpected));
    }
}
