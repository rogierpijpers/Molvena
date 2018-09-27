package com.capgemini.web;

import com.capgemini.domain.Guest;
import com.capgemini.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class RegisterController {

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(value = "/createRegistration", method = RequestMethod.POST)
    public Guest createRegistration(@RequestBody Guest guest) {
        this.registrationService.AddRegistration(guest);
        return guest;
    }

    @RequestMapping(value = "/getRegistrations/{name}", method = RequestMethod.GET)
    public Guest getRegistration(@PathVariable("name") String name) {
        return(this.registrationService.getRegistrationByName(name));
    }
}
