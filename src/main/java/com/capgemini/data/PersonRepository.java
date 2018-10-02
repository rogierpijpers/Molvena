package com.capgemini.data;

import com.capgemini.domain.Person;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Repository
@Component
@Service
public class PersonRepository {
    private List<Person> persons;

    public PersonRepository(){
        persons = new ArrayList<>();
    }

    public List<Person> getPersons() {
        return persons;
    }

    public Person getSinglePerson(String mail){
        for (Person person : persons) {
            if (person.getMail().equals(mail)){
                return person;
            }
        }
        return null;
    }

    public void addPerson(Person person){
        persons.add(person);
    }

    public void updatePerson(String username, Person person){
        Person toReplace = getSinglePerson(username);
        int index = persons.indexOf(toReplace);
        persons.set(index, person);
    }
}
