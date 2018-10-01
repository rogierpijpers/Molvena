package com.capgemini.web;

import com.capgemini.domain.Guest;
import com.capgemini.domain.Room;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

    @Autowired
    private RoomService roomService;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testCreateRoom(){

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
                .andExpect(content().json(jsonExpected));;
    }
}
