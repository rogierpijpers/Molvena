package com.capgemini.web;

import com.capgemini.data.GuestRepository;
import com.capgemini.domain.Guest;
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
public class GuestControllerTest {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GuestRepository guestRepository;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    @WithMockUser(username="Jan@vandijk.nl", roles={"GUEST"})
    public void testUpdateGuest() throws Exception {
        Guest guest = guestRepository.getGuestByUsername("Jan@vandijk.nl");
        Assert.assertTrue(guest.getFirstName().equals("Jan"));

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(df);
        String jsonExpected = objectMapper.writeValueAsString(guest);

        guest.setFirstName("Peter");
        this.mockMvc.perform(put("/guest/Jan@vandijk.nl").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonExpected)).andDo(print()).andExpect(status().isOk());

        this.mockMvc.perform(get("/guest/Jan@vandijk.nl")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(jsonExpected));;
    }

    @Test
    @WithMockUser(username="Jan@vandijk.nl", roles={"GUEST"})
    public void testUpdateGuestInvalidInput() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonExpected = objectMapper.writeValueAsString("");

        this.mockMvc.perform(put("/guest/Jan@vandijk.nl").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonExpected)).andDo(print()).andExpect(status().isBadRequest());
    }
}
