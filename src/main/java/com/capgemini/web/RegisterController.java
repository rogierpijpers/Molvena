package com.capgemini.web;

import com.capgemini.domain.Guest;
import com.capgemini.domain.Person;
import com.capgemini.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.EmptyStackException;

@RestController
public class RegisterController {

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping("/register")
    @PostMapping
    public Person createRegistration(@RequestBody Guest guest) {

        if (guest.equals(null)) {
            throw new NullPointerException();
        }

        this.registrationService.AddRegistration(guest);
        return guest;
    }
}
