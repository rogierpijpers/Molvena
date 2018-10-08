package com.capgemini.web;

import com.capgemini.data.EmployeeRepository;
import com.capgemini.domain.Employee;
import com.capgemini.domain.Guest;
import com.capgemini.service.RegistrationService;
import com.capgemini.web.authentication.AuthenticationHelper;
import com.capgemini.web.util.exception.InvalidInputException;
import com.capgemini.web.util.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.InvalidObjectException;
import java.util.List;

@RestController
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Secured({"ROLE_RECEPTIONIST","ROLE_ADMIN"})
    @RequestMapping("/employee/")
    public List<Employee> getAllEmployees(){
        return employeeRepository.getAllEmployees();
    }

    @Secured({"ROLE_RECEPTIONIST", "ROLE_ADMIN"})
    @RequestMapping("/employee/{username}")
    public Employee getEmployeeByUsername(@PathVariable("username") String username) {
        return employeeRepository.getEmployeeByUsername(username);
    }

    @Secured({"ROLE_RECEPTIONIST", "ROLE_ADMIN"})
    @RequestMapping(value="/employee/{username}", method= RequestMethod.PUT)
    public void updateEmployee(@PathVariable("username") String username, @RequestBody Employee employee) throws UnauthorizedException, InvalidInputException {
        // Spring Boot returns a 400 error if PUT body is empty, but just in case...
        if(username == null || username.equals("") || employee == null)
            throw new InvalidInputException("Invalid input.");

        if(AuthenticationHelper.userIsReceptionist()) {
            String loggedInUsername = AuthenticationHelper.getCurrentUsername();

            if(employee.getMail().equals(loggedInUsername))
                employeeRepository.updateEmployee(username, employee);

        } else if (AuthenticationHelper.userIsAdmin()){
            employeeRepository.updateEmployee(username, employee);
        } else {
            throw new UnauthorizedException();
        }
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RegistrationService registrationService;

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/employee/", method = RequestMethod.POST)
    public Employee createEmployee (@RequestBody Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        this.registrationService.AddRegistration(employee);
        return employee;
    }
}