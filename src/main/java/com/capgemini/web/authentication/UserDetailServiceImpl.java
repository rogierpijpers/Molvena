package com.capgemini.web.authentication;

import com.capgemini.data.PersonBaseRepository;
import com.capgemini.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Arrays;

public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private PersonBaseRepository personBaseRepository;

    @Override
    public User loadUserByUsername(String username) {
        Person person = personBaseRepository.findByMail(username);
        if(person == null){
            throw new BadCredentialsException("Bad credentials");
        }
        return mapPersonToUser(person);
    }

    private User mapPersonToUser(Person person){
        User user = new User(person.getMail(), person.getPassword(), Arrays.asList(new SimpleGrantedAuthority(person.getRole())));

        return user;
    }
}
