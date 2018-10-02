package com.capgemini.web;

import com.capgemini.data.GuestRepository;
import com.capgemini.domain.Guest;
import com.capgemini.service.RegistrationService;
import com.capgemini.web.authentication.AuthenticationHelper;
import com.capgemini.web.util.exception.InvalidInputException;
import com.capgemini.web.util.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GuestController {

    @Autowired
    private GuestRepository guestRepository;

    @Secured({"ROLE_ADMIN", "ROLE_RECEPTIONIST"})
    @RequestMapping("/guest/")
    public List<Guest> getAllGuests(){
        return guestRepository.getAllGuests();
    }

    @Secured({"ROLE_GUEST", "ROLE_ADMIN"})
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

    @Secured({"ROLE_GUEST", "ROLE_ADMIN"})
    @RequestMapping(value="/guest/{username}", method= RequestMethod.PUT)
    public void updateGuest(@PathVariable("username") String username, @RequestBody Guest guest) throws UnauthorizedException, InvalidInputException {
        // Spring Boot returns a 400 error if PUT body is empty, but just in case...
        if(username == null && username.equals("") && guest == null)
            throw new InvalidInputException("Invalid input.");

        if(AuthenticationHelper.userIsGuest()){
            String loggedInUsername = AuthenticationHelper.getCurrentUsername();
            if(guest.getMail().equals(loggedInUsername)){
                guestRepository.updateGuest(username, guest);
            }else{
                throw new UnauthorizedException();
            }
        }else{
            guestRepository.updateGuest(username, guest);
        }
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(value = "/guest/", method = RequestMethod.POST)
    public Guest createGuest(@RequestBody Guest guest) {
        guest.setPassword(passwordEncoder.encode(guest.getPassword()));
        this.registrationService.AddRegistration(guest);
        return guest;
    }
}
