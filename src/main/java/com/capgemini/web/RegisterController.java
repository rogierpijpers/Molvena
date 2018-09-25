package com.capgemini.web;

import com.capgemini.domain.Person;
import com.capgemini.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping("/register")
    public String createRegistrationPage(){
        return "html/register";
    }
//    @PostMapping
//    public Person createRegistration(@RequestBody Person person){
//        this.registrationService.AddRegistration(person);
//        return person;

}
