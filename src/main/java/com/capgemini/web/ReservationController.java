package com.capgemini.web;

import com.capgemini.domain.Reservation;
import com.capgemini.domain.RoomType;
import com.capgemini.service.ReservationService;
import com.capgemini.web.authentication.AuthenticationHelper;
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
    @RequestMapping("/reservation/")
    public List<Reservation> getReservationById(){
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
        throw new Exception("I don't exist yet.");
    }

    @Secured({"ROLE_GUEST", "ROLE_ADMIN"})
    @RequestMapping(value="/reservation/{id}", method=RequestMethod.DELETE)
    public void deleteReservation(@PathVariable("id") int id) throws InvalidObjectException {
        service.softDelete(service.getReservationByID(id));
    }
}