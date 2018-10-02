package com.capgemini.web;

import com.capgemini.data.PersonBaseRepository;
import com.capgemini.data.PersonRepository;
import com.capgemini.domain.Person;
import com.capgemini.web.authentication.AuthenticationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller is secured by WebSecurityConfig, it is required to be logged in to access /acount
 */
@RestController
public class AccountController {
    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("/account")
    public Person getCurrentUser(){
        String username = AuthenticationHelper.getCurrentUsername();
        Person person = personRepository.findByMail(username);
        person.setPassword("");
        return person;
    }
}
