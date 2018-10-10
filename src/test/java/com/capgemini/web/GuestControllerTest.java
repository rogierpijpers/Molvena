package com.capgemini.web;

import com.capgemini.TestJpaConfig;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.TimeZone;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private GuestRepository guestRepository;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    @WithMockUser(username="Henk@vanvliet.nl", roles={"ADMIN"})
    public void testCreateGuestWithChineseCharacters() throws Exception{

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(df);

        Guest guest = new Guest();
        guest.setPassword("爱蔼爱蔼爱蔼爱蔼");
        guest.setFirstName("爱");
        guest.setLastName("蔼");
        guest.setMail("普里@阿育王。印度");
        guest.setZipCode("3242AD");
        guest.setDateOfBirth(new Date());
        guest.setPhone("0623402340");
        guest.setAddress("");
        guest.setCountry("CN");
        guest.setCity("Hongkong");
        guest.setState("Hongkong");

        String guestWithChineseCharactersInNameAsJsonString = objectMapper.writeValueAsString(guest);

        this.mockMvc.perform(post("/guest/").contentType(MediaType.APPLICATION_JSON).content(guestWithChineseCharactersInNameAsJsonString))
                .andExpect(status().isOk());

        // Get this guest
        MvcResult mvcResult = this.mockMvc.perform(get("/guest/普里@阿育王。印度").characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String responseJson = mvcResult.getResponse().getContentAsString();
        Guest responseGuest = objectMapper.readValue(responseJson, Guest.class);

        Assert.assertEquals(guest.getMail(), responseGuest.getMail());
        Assert.assertEquals(guest.getFirstName(), responseGuest.getFirstName());
        Assert.assertEquals(guest.getLastName(), responseGuest.getLastName());
        Assert.assertTrue(passwordEncoder.matches(guest.getPassword(), responseGuest.getPassword()));
    }

    @Test
    @WithMockUser(username="Jan@vandijk.nl", roles={"GUEST"})
    public void testUpdateGuest() throws Exception {
        Guest guest = guestRepository.findByMail("Jan@vandijk.nl");
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
                .andExpect(content().json(jsonExpected));
    }

    @Test
    @WithMockUser(username="Jan@vandijk.nl", roles={"GUEST"})
    public void testUpdateGuestInvalidInput() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonExpected = objectMapper.writeValueAsString("");

        this.mockMvc.perform(put("/guest/Jan@vandijk.nl").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonExpected)).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username="Jan@vandijk.nl", roles={"GUEST"})
    public void testDeleteGuestAsGuest() throws Exception {
        this.mockMvc.perform(delete("/guest/2")).andDo(print()).andExpect(status().isOk());
    }

}
