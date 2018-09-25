package com.capgemini.web.molvenolakeresort;

import com.capgemini.domain.Person;
import com.capgemini.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public Person createRegistration(@RequestBody Person person){
        this.registrationService.AddRegistration(person);
        return person;
    }
}
