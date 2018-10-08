package com.capgemini.web;

import com.capgemini.data.GuestRepository;
import com.capgemini.domain.Reservation;
import com.capgemini.service.ReservationService;
import com.capgemini.web.authentication.AuthenticationHelper;
import com.capgemini.web.util.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.io.InvalidObjectException;
import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    private ReservationService service;

    @Autowired
    private GuestRepository guestRepository;

    @Secured({"ROLE_GUEST", "ROLE_ADMIN", "ROLE_RECEPTIONIST"})
    @RequestMapping("/reservation/{id}")
    public Reservation getReservationById(@PathVariable("id") int id){
        if(AuthenticationHelper.userIsGuest()){
            String username = AuthenticationHelper.getCurrentUsername();
            return service.getReservationByIdForGuest(id, username);
        }else{
            return service.getReservationByID(id);
        }
    }

    @Secured({"ROLE_GUEST", "ROLE_RECEPTIONIST", "ROLE_ADMIN"})
    @RequestMapping("/reservation/user/{username}")
    public ResponseEntity<List<Reservation>> getReservationsByUsername(@PathVariable("username") String username) throws UnauthorizedException {
        if(guestRepository.getGuestByUsername(username)!= null) {
            if (AuthenticationHelper.userIsGuest()) {
                String loggedInUsername = AuthenticationHelper.getCurrentUsername();
                if (username.equals(loggedInUsername))
                    return ResponseEntity.ok(service.getReservationsByUsername(username));
                else
                    throw new UnauthorizedException();
            } else {
                return ResponseEntity.ok(service.getReservationsByUsername(username));
            }
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @Secured({"ROLE_GUEST", "ROLE_RECEPTIONIST", "ROLE_ADMIN"})
    @RequestMapping("/reservation/")
    public List<Reservation> getAllReservations(){
        if(AuthenticationHelper.userIsGuest()){
            String username = AuthenticationHelper.getCurrentUsername();
            return service.getReservationsByUsername(username);
        }else{
            return service.getAllReservations();
        }
    }

    @Secured({"ROLE_GUEST", "ROLE_RECEPTIONIST", "ROLE_ADMIN"})
    @RequestMapping(value="/reservation/", method=RequestMethod.POST)
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation){
        if(guestRepository.getGuestByUsername(reservation.getGuest().getMail())!= null){
            service.addReservation(reservation);
            return ResponseEntity.ok(reservation);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @Secured({"ROLE_ADMIN", "ROLE_RECEPTIONIST", "ROLE_ADMIN"})
    @RequestMapping(value="/reservation/{id}", method=RequestMethod.PUT)
    public void editReservationById(@PathVariable("id") int id, @RequestBody Reservation reservation) throws Exception {
        Reservation oldReservation = service.getReservationByID(id);
        if(AuthenticationHelper.userIsGuest()){
            if(reservation.getGuest().getMail() == AuthenticationHelper.getCurrentUsername()){
                service.updateReservation(id, reservation);
            } else {
                throw new UnauthorizedException("You can not edit the reservation of someone else");
            }
        } else {
            service.updateReservation(id, reservation);
        }
    }

    @Secured({"ROLE_GUEST", "ROLE_RECEPTIONIST", "ROLE_ADMIN"})
    @RequestMapping(value="/reservation/{id}", method=RequestMethod.DELETE)
    public void deleteReservation(@PathVariable("id") int id) throws InvalidObjectException {
        Reservation reservation = service.getReservationByID(id);
        if(AuthenticationHelper.userIsGuest()){
            if(reservation.getGuest().getMail() == AuthenticationHelper.getCurrentUsername()){
                service.softDelete(service.getReservationByID(id));
            }
        } else {
            service.softDelete(service.getReservationByID(id));
        }
    }
}
