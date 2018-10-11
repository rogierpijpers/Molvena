package com.capgemini.web;

import com.capgemini.data.GuestRepository;
import com.capgemini.domain.Guest;
import com.capgemini.service.GuestService;
import com.capgemini.service.RegistrationService;
import com.capgemini.web.authentication.AuthenticationHelper;
import com.capgemini.web.util.exception.InvalidInputException;
import com.capgemini.web.util.exception.ObjectNotFoundException;
import com.capgemini.web.util.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class GuestController {

    @Autowired
    private GuestService guestService;

    @Secured({"ROLE_ADMIN", "ROLE_RECEPTIONIST"})
    @RequestMapping("/guest/")
    public Iterable<Guest> getAllGuests(){
        return guestService.getAllGuests();
    }

    @Secured({"ROLE_GUEST", "ROLE_ADMIN", "ROLE_RECEPTIONIST"})
    @RequestMapping("/guest/{username}")
    public Guest getGuestByUsername(@PathVariable("username") String username) throws UnauthorizedException {
        if(AuthenticationHelper.userIsGuest()) {
            String loggedInUsername = AuthenticationHelper.getCurrentUsername();
            if(username.equals(loggedInUsername))
                return guestService.getGuestByUsername(username);
            else
                throw new UnauthorizedException();
        }else if(AuthenticationHelper.userIsAdmin() || AuthenticationHelper.userIsReceptionist()){
            return guestService.getGuestByUsername(username);
        } else {
            throw new UnauthorizedException("You are not logged in");
        }
    }

    @Secured({"ROLE_GUEST", "ROLE_RECEPTIONIST","ROLE_ADMIN"})
    @RequestMapping(value="/guest/{username}", method= RequestMethod.PUT)
    public void updateGuest(@PathVariable("username") String username, @RequestBody Guest guest) throws UnauthorizedException, InvalidInputException {
        // Spring Boot returns a 400 error if PUT body is empty, but just in case...
        if(username == null || username.equals("") || guest == null)
            throw new InvalidInputException("Invalid input.");

        if(AuthenticationHelper.userIsGuest()){
            String loggedInUsername = AuthenticationHelper.getCurrentUsername();
            if(guest.getMail().equals(loggedInUsername)){
                guestService.createGuest(guest);
            }else{
                throw new UnauthorizedException("You are trying to update someone else's account");
            }
            guestService.createGuest(guest);
        }else if(AuthenticationHelper.userIsAdmin() || AuthenticationHelper.userIsReceptionist()){
            guestService.createGuest(guest);
        } else {
            throw new UnauthorizedException("This role is not allowed to update guests");
        }
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(value = "/guest/", method = RequestMethod.POST)
    public Guest createGuest(@RequestBody Guest guest) {
        guest.setPassword(passwordEncoder.encode(guest.getPassword()));
        guest.setMail(guest.getMail().toLowerCase());
        this.registrationService.AddRegistration(guest);
        return guest;
    }

    @Secured({"ROLE_GUEST", "ROLE_RECEPTIONIST","ROLE_ADMIN"})
    @RequestMapping(value = "/guest/{id}", method = RequestMethod.DELETE)
    public void softDeleteGuest(@PathVariable long id) throws UnauthorizedException, ObjectNotFoundException {
        if(AuthenticationHelper.userIsGuest()) {
            if(guestService.getGuestByUsername(AuthenticationHelper.getCurrentUsername()).getId() == id){
                guestService.deleteGuest(id);
            } else {
                throw new UnauthorizedException("You can not delete someone else on this role");
            }
        } else {
            guestService.deleteGuest(id);
        }
    }
}
