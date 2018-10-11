package com.capgemini.service;

import com.capgemini.data.GuestRepository;
import com.capgemini.domain.Guest;
import com.capgemini.web.util.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestService {
    @Autowired
    private GuestRepository guestRepository;

    public List<Guest> getAllGuests(){
        return guestRepository.findAll();
    }

    public Guest getGuestByUsername(String mail){
        return guestRepository.findByMail(mail);
    }

    public void createGuest(Guest guest){
        guestRepository.save(guest);
    }

    public void updateGuest(long id, Guest guest){

    }

    public void deleteGuest(long id) throws ObjectNotFoundException {
        Optional<Guest> guest = guestRepository.findById(id);
        if(guest.isPresent())
            guestRepository.delete(guest.get());
        else
            throw new ObjectNotFoundException();
    }
}
