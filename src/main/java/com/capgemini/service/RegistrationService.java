package com.capgemini.service;

import com.capgemini.data.EmployeeRepository;
import com.capgemini.data.GuestRepository;
import com.capgemini.domain.Employee;
import com.capgemini.domain.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class RegistrationService {

    @Autowired
    private GuestRepository guestRepository;
    private EmployeeRepository employeeRepository;

    public void setGuestRepository(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public RegistrationService() {
        guestRepository = new GuestRepository();
    }

    public void AddRegistration(Guest guest) {
            guestRepository.addGuest(guest);
    }

    public void AddRegistration(Employee employee) {
        employeeRepository.addEmployee(employee);
    }

    public Guest getRegistrationByName(String username) {
        return(guestRepository.getGuestByUsername(username));
    }
}
