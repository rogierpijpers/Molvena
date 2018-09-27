package com.capgemini.web;

import com.capgemini.data.GuestRepository;
import com.capgemini.domain.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GuestController {

    @Autowired
    private GuestRepository guestRepository;

    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/guest/")
    public List<Guest> getAllGuests(){
        return guestRepository.getAllGuests();
    }

    @Secured({"ROLE_GUEST", "ADMIN"})
    @RequestMapping("/guest/{id}")
    public Guest getGuestById(@PathVariable("id") int id){
        // TODO: a guest can only get his own ID
        return null;
    }

    @Secured({"ROLE_GUEST", "ADMIN"})
    @RequestMapping(value="/guest/{id}", method= RequestMethod.PUT)
    public void updateGuest(@PathVariable("id") int id, @RequestBody Guest guest){
        // TODO: a guest can only update his own information
    }

    @Secured({"ROLE_GUEST", "ADMIN"})
    @RequestMapping(value="/guest/", method= RequestMethod.POST)
    public void createGuest(@RequestBody Guest guest){
        // TODO: this is redundant with /registration controller...
    }

}
