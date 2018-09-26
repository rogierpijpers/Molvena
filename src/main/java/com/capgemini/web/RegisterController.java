package com.capgemini.web;

import com.capgemini.domain.Guest;
import com.capgemini.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public Guest createRegistration(@RequestBody Guest guest) {
        this.registrationService.AddRegistration(guest);
        return guest;
    }
}
