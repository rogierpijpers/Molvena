package com.capgemini.web.authentication;

import com.capgemini.data.PersonRepository;
import com.capgemini.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Arrays;

public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public User loadUserByUsername(String username) {
        Person person = personRepository.getSinglePerson(username);
        return mapPersonToUser(person);
    }

    // TODO: Add roles
    private User mapPersonToUser(Person person){
        User user = new User(person.getMail(), person.getPassword(), Arrays.asList(new SimpleGrantedAuthority("USER")));
        return user;
    }
}
