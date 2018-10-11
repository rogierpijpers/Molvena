package com.capgemini.web;

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

        // Currently disabled to fix bug with password resetting
        // when performing a post request with an empty password string.
        // Todo: either remove code and allow passwords in guest object, or remove key all together from returned guest object.
        // person.setPassword("");
        return person;
    }

    @RequestMapping("/account/check")
    public boolean isLoggedIn() {
        return AuthenticationHelper.isLoggedIn();
    }
}
