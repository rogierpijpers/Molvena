package com.capgemini.web;

import com.capgemini.domain.Reservation;
import com.capgemini.domain.RoomType;
import com.capgemini.service.ReservationService;
import com.capgemini.web.authentication.AuthenticationHelper;
import com.capgemini.web.util.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.io.InvalidObjectException;
import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    private ReservationService service;

    @Secured({"ROLE_GUEST", "ROLE_ADMIN"})
    @RequestMapping("/reservation/{id}")
    public Reservation getReservationById(@PathVariable("id") int id){
        if(AuthenticationHelper.userIsGuest()){
            String username = AuthenticationHelper.getCurrentUsername();
            return service.getReservationByIdForGuest(id, username);
        }else{
            return service.getReservationByID(id);
        }
    }

    @Secured({"ROLE_GUEST", "ROLE_ADMIN"})
    @RequestMapping("/reservation/user/{username}")
    public List<Reservation> getReservationsByUsername(@PathVariable("username") String username) throws UnauthorizedException {
        if(AuthenticationHelper.userIsGuest()) {
            String loggedInUsername = AuthenticationHelper.getCurrentUsername();
            if(username.equals(loggedInUsername))
                return service.getReservationsByUsername(username);
            else
                throw new UnauthorizedException();
        }else{
            return service.getReservationsByUsername(username);
        }
    }

    // TODO: get reservation by name

    @Secured({"ROLE_GUEST", "ROLE_ADMIN"})
    @RequestMapping("/reservation/")
    public List<Reservation> getAllReservations(){
        if(AuthenticationHelper.userIsGuest()){
            String username = AuthenticationHelper.getCurrentUsername();
            return service.getReservationsByUsername(username);
        }else{
            return service.getAllReservations();
        }
    }

    @Secured({"ROLE_GUEST", "ROLE_ADMIN"})
    @RequestMapping(value="/reservation/", method=RequestMethod.POST)
    public void createReservation(@RequestBody Reservation reservation) {
        service.addReservation(reservation);
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value="/reservation/{id}", method=RequestMethod.PUT)
    public void editReservationById(@PathVariable("id") int id, @RequestBody Reservation reservation) throws Exception {
        service.updateReservation(id, reservation);
    }

    @Secured({"ROLE_GUEST", "ROLE_ADMIN"})
    @RequestMapping(value="/reservation/{id}", method=RequestMethod.DELETE)
    public void deleteReservation(@PathVariable("id") int id) throws InvalidObjectException {
        service.softDelete(service.getReservationByID(id));
    }
}