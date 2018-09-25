package com.capgemini.data;

import com.capgemini.domain.Person;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RegistrationRepository {

    private List<Person> registrations = new ArrayList<>();

    public List<Person> getAllRegistrations(){
        return registrations;
    }

    public void addRegistration(Person person){
        registrations.add(person);
    }
}

