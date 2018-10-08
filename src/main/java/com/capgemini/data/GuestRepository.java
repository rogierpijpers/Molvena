package com.capgemini.data;

import com.capgemini.domain.Guest;
import com.capgemini.domain.Person;
import com.capgemini.web.authentication.AuthenticationHelper;
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
        guest.setId(personRepository.getPersons().size()+1);
        personRepository.addPerson((Person) guest);
    }

    public Guest getGuestById(int id){
        return getAllGuests().stream().filter(x -> x.getId()== id).findFirst().orElse(null);
    }

    public Guest getGuestByUsername(String username){
        return getAllGuests().stream().filter(x -> x.getMail().equals(username)).findFirst().orElse(null);
    }

    public void updateGuest(String username, Guest guest){
        personRepository.updatePerson(username, guest);
    }

    public void deleteGuest(int id){
        Guest user = getGuestById(id);
        Person person = personRepository.getSinglePerson(user.getMail());
        personRepository.deletePerson(person);
    }
}
