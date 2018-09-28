package com.capgemini.data;

import com.capgemini.domain.Guest;
import com.capgemini.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GuestRepository {
    @Autowired
    private PersonRepository personRepository;

    public List<Guest> getAllGuests(){
        return personRepository.getPersons()
                .stream().filter(x -> x instanceof Guest)
                .map(x-> (Guest) x)
                .collect(Collectors.toList());
    }

    public void addGuest(Guest guest){
        personRepository.addPerson((Person) guest);
    }

    public Guest getGuestByUsername(String username){
        return getAllGuests().stream().filter(x -> x.getMail().equals(username)).findFirst().orElse(null);
    }
}
