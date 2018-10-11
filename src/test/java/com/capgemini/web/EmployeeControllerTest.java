package com.capgemini.web;

import com.capgemini.TestJpaConfig;
import com.capgemini.data.EmployeeRepository;
import com.capgemini.domain.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeController employeeController;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    private Employee createMockEmployee(){
        Employee employee = new Employee();
        employee.setFirstName("Henk");
        employee.setLastName("van Vliet");
        employee.setPhone("123456789");
        employee.setPassword("$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2");
        employee.setMail("Henk@vanvliet.nl");
        employee.setAddress("Straat 1");
        employee.setZipCode("5555LL");
        employee.setState("");
        employee.setCountry("NL");
        employee.setDateOfBirth(new Date(31-8-1994));
        employee.setRole("ROLE_ADMIN");
        return employee;
    }

    @Test
    @WithMockUser(username="Henk@vanvliet.nl", roles={"ADMIN"})
    public void testUpdateEmployee() throws Exception {
        when(employeeRepository.findByMail("Henk@vanvliet.nl")).thenReturn(createMockEmployee());

        Employee employee = employeeRepository.findByMail("Henk@vanvliet.nl");
        Assert.assertTrue(employee.getFirstName().equals("Henk"));

        employee.setFirstName("Peter");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(df);
        String jsonExpected = objectMapper.writeValueAsString(employee);

        this.mockMvc.perform(put("/employee/Henk@vanvliet.nl").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonExpected)).andDo(print()).andExpect(status().isOk());

        when(employeeRepository.findByMail("Henk@vanvliet.nl")).thenReturn(employee);

        this.mockMvc.perform(get("/employee/Henk@vanvliet.nl")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(jsonExpected));
    }

    @Test
    @WithMockUser(username="Henk@vanvliet.nl", roles={"ADMIN"})
    public void testUpdateEmployeeInvalidInput() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonExpected = objectMapper.writeValueAsString("");

        this.mockMvc.perform(put("/employee/Henk@vanvliet.nl").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonExpected)).andDo(print()).andExpect(status().isBadRequest());
    }
}
