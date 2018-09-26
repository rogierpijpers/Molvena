package com.capgemini.service;

import com.capgemini.data.RegistrationRepository;
import com.capgemini.domain.Guest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class RegistrationService {

    private RegistrationRepository registrationRepository;

    public void setRegistrationRepository(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public RegistrationService() {
        registrationRepository = new RegistrationRepository();
    }

    public void AddRegistration(
//            String firstName,
//            String lastName,
//            String password,
//            Date dateOfBirth,
//            String mail,
//            String phone,
//            String address,
//            String city,
//            String zipCode,
//            String country
            Guest guest
    )
    {
        if(guest != null){
            registrationRepository.addRegistration(guest);
        }
//        Person person = new Person();
//        person.setFirstName(firstName);
//        person.setLastName(lastName);
//        person.setPassword(password);
//        person.setDateOfBirth(dateOfBirth);
//        person.setMail(mail);
//        person.setPhone(phone);
//        person.setAddress(address);
//        person.setCity(city);
//        person.setZipCode(zipCode);
//        person.setCountry(country);
        }
    }
