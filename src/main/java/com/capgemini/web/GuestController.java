package com.capgemini.web;

import com.capgemini.data.GuestRepository;
import com.capgemini.domain.Guest;
import com.capgemini.web.authentication.AuthenticationHelper;
import com.capgemini.web.util.exception.UnauthorizedException;
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
    @RequestMapping("/guest/{username}")
    public Guest getGuestByUsername(@PathVariable("username") String username) throws UnauthorizedException {
        if(AuthenticationHelper.userIsGuest()) {
            String loggedInUsername = AuthenticationHelper.getCurrentUsername();
            if(username.equals(loggedInUsername))
                return guestRepository.getGuestByUsername(username);
            else
                throw new UnauthorizedException();
        }else{
            return guestRepository.getGuestByUsername(username);
        }
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
