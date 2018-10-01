package com.capgemini.web;

import com.capgemini.data.EmployeeRepository;
import com.capgemini.domain.Employee;
import com.capgemini.service.RegistrationService;
import com.capgemini.web.authentication.AuthenticationHelper;
import com.capgemini.web.util.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    public Employee getEmployeeByUsername(@PathVariable("username") String username) throws UnauthorizedException {
        if(AuthenticationHelper.userIsReceptionist() || AuthenticationHelper.userIsAdmin()) {
            String loggedInUsername = AuthenticationHelper.getCurrentUsername();
            if(username.equals(loggedInUsername))
                return employeeRepository.getEmployeeByUsername(username);
            else
                throw new UnauthorizedException();
        } else {
            return employeeRepository.getEmployeeByUsername(username);
        }
    }

    @Secured({"ROLE_RECEPTIONIST", "ROLE_ADMIN"})
    @RequestMapping(value="/employee/{username}", method= RequestMethod.PUT)
    public void updateEmployee(@PathVariable("username") String username, @RequestBody Employee employee) throws UnauthorizedException{

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

    @RequestMapping(value = "/createEmployee", method = RequestMethod.POST)
    public Employee createRegistration(@RequestBody Employee employee) {
        //TODO: fix
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));

        return employee;
    }


}