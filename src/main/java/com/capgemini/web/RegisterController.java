package com.capgemini.web;

import com.capgemini.domain.Guest;
import com.capgemini.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class RegisterController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(value = "/createRegistration", method = RequestMethod.POST)
    public Guest createRegistration(@RequestBody Guest guest) {
        guest.setPassword(passwordEncoder.encode(guest.getPassword()));
        this.registrationService.AddRegistration(guest);
        return guest;
    }
}
