package com.capgemini.data;

import com.capgemini.domain.Guest;
import com.capgemini.domain.Person;
import com.capgemini.domain.Receptionist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReceptionistRepository {
    @Autowired
    private PersonRepository personRepository;

    public List<Receptionist> getAllReceptionists(){
        return personRepository.getPersons()
                .stream().filter(x -> x instanceof Receptionist)
                .map(x-> (Receptionist) x)
                .collect(Collectors.toList());
    }

    public void addReceptionist(Receptionist receptionist){
        personRepository.addPerson((Person) receptionist);
    }
}
