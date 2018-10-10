package com.capgemini.web;

import com.capgemini.data.PersonRepository;
import com.capgemini.domain.Guest;
import com.capgemini.domain.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private PersonRepository personRepository;

    @InjectMocks
    private AccountController accountController;

    private Person mockPerson;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        MockitoAnnotations.initMocks(this);
        createMockPerson();
    }

    private void createMockPerson(){
        Guest guest = new Guest();
        guest.setFirstName("Jan");
        guest.setLastName("van Dijk");
        guest.setPhone("06-87654321");
        guest.setPassword("$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2");
        guest.setMail("Jan@vandijk.nl");
        guest.setAddress("Straat 1");
        guest.setZipCode("5555LL");
        guest.setState("");
        guest.setCountry("NL");
        guest.setDateOfBirth(new Date(31-8-1994));

        mockPerson = guest;
    }

    private User createSpringUser(Person person){
        return new User(person.getMail(), person.getPassword(), Arrays.asList(new SimpleGrantedAuthority(person.getRole())));
    }

    @Test
    @WithMockUser(username="Jan@vandijk.nl", roles={"GUEST"})
    public void testGetCurrentUser() throws Exception {
        mockPerson.setPassword("");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(df);
        String jsonExpected = objectMapper.writeValueAsString(mockPerson);

        when(personRepository.findByMail("Jan@vandijk.nl")).thenReturn(mockPerson);

        this.mockMvc.perform(get("/account")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(jsonExpected));
    }
}
