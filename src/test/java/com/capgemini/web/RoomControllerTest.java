package com.capgemini.web;

import com.capgemini.domain.Guest;
import com.capgemini.domain.Room;
import com.capgemini.domain.RoomType;
import com.capgemini.service.RoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class RoomControllerTest {
//    @Autowired
//    private WebApplicationContext wac;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private RoomService roomService;
//
//    @Before
//    public void setup() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//    }

//    @Test
//    @WithMockUser(username="Henk@vanvliet.nl", roles={"ADMIN"})
//    public void testDeleteRoom() throws Exception {
//        Room room = roomService.getRoomById((short) 0);
//        if(room == null){
//            room = new Room();
//            room.setRoomID((short) 0);
//            room.setRoomType(new RoomType((byte) 2, (byte) 2));
//            roomService.createRoom(room);
//        }
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonExpected = objectMapper.writeValueAsString(room);
//
//        this.mockMvc.perform(get("/room/0")).andDo(print()).andExpect(status().isOk()).andExpect(content().json(jsonExpected));
//        this.mockMvc.perform(delete("/room/0")).andDo(print()).andExpect(status().isOk());
//        this.mockMvc.perform(get("/room/0")).andExpect(status().isNotFound());
//    }
//
//    @Test
//    @WithMockUser(username="Henk@vanvliet.nl", roles={"ADMIN"})
//    public void testCreateRoom() throws Exception {
//        Room room = new Room();
//        room.setRoomID((short) 5432);
//        room.setRoomType(new RoomType((byte) 2, (byte) 2));
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonExpected = objectMapper.writeValueAsString(room);
//
//        this.mockMvc.perform(post("/room/").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonExpected)).andDo(print()).andExpect(status().isOk());
//        this.mockMvc.perform(get("/room/5432")).andDo(print()).andExpect(status().isOk()).andExpect(content().json(jsonExpected));
//    }
//
//    @Test
//    @WithMockUser(username="Henk@vanvliet.nl", roles={"ADMIN"})
//    public void testUpdateRoom() throws Exception{
//        Room room = roomService.getAllRooms().get(0);
//        Assert.assertTrue(room.getRoomID() == 0);
//
//        Room newRoom = new Room();
//        newRoom.setRoomID((short) 99);
//        newRoom.setRoomType(room.getRoomType());
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonExpected = objectMapper.writeValueAsString(newRoom);
//
//        this.mockMvc.perform(put("/room/0").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonExpected)).andDo(print()).andExpect(status().isOk());
//
//        this.mockMvc.perform(get("/room/99")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().json(jsonExpected));
//    }
//}
