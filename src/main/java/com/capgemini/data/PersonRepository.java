package com.capgemini.data;

import com.capgemini.domain.Guest;
import com.capgemini.domain.Person;
import com.capgemini.domain.Receptionist;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Component
@Service
public class PersonRepository {
    private List<Person> persons;

    public PersonRepository(){
        persons = new ArrayList<>();
        addInitialAccounts();
    }

    public void addInitialAccounts(){
        Person person1 = new Guest();
        person1.setFirstName("Thom");
        person1.setLastName("vd Moosdijk");
        person1.setPhone("123456789");
        person1.setPassword("$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2");
        person1.setMail("Thom@moosjes.nl");
        person1.setAddress("Straat 1");
        person1.setZipCode("5555LL");
        person1.setCountry("NL");
        person1.setDateOfBirth(new Date(31-8-1994));
        persons.add(person1);

        Person person2 = new Guest();
        person2.setFirstName("Jan");
        person2.setLastName("van Dijk");
        person2.setPhone("123456789");
        person2.setPassword("$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2");
        person2.setMail("Jan@vandijk.nl");
        person2.setAddress("Straat 1");
        person2.setZipCode("5555LL");
        person2.setCountry("NL");
        person2.setDateOfBirth(new Date(31-8-1994));
        persons.add(person2);

        Person receptionist1 = new Receptionist();
        receptionist1.setFirstName("Henk");
        receptionist1.setLastName("van Vliet");
        receptionist1.setPhone("123456789");
        receptionist1.setPassword("$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2");
        receptionist1.setMail("Henk@vanvliet.nl");
        receptionist1.setAddress("Straat 1");
        receptionist1.setZipCode("5555LL");
        receptionist1.setCountry("NL");
        receptionist1.setDateOfBirth(new Date(31-8-1994));
        persons.add(receptionist1);
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
}
