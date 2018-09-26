package com.capgemini.data;

import com.capgemini.domain.Guest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RegistrationRepository {

    private List<Guest> registrations = new ArrayList<>();

        public List<Guest> getAllRegistrations(){
            return registrations;
    }

    public void addRegistration(Guest guest){
            registrations.add(guest);
    }

}

