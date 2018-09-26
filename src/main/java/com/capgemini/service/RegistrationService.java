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

    public void AddRegistration(Guest guest) {
            registrationRepository.addRegistration(guest);
    }
}
