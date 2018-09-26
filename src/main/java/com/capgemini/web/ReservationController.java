package com.capgemini.web;

import com.capgemini.domain.Reservation;
import com.capgemini.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.InvalidObjectException;
import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    private ReservationService service;

    @RequestMapping("/reservation/{id}")
    public Reservation getReservationById(int id){
        return service.getReservationByID(id);
    }

    @RequestMapping("/reservation/")
    public List<Reservation> getReservationById(){
        return service.getAllReservations();
    }

    @RequestMapping(value="/reservation/{id}", method=RequestMethod.POST)
    public void createReservation(Reservation reservation) {
        service.addReservation(reservation);
    }

    @RequestMapping(value="/reservation/{id}", method=RequestMethod.PUT)
    public void editReservationById(int id) {
        throw new NotImplementedException();
    }

    @RequestMapping(value="/reservation/{id}", method=RequestMethod.DELETE)
    public void deleteReservation(int id) throws InvalidObjectException {
        service.softDelete(service.getReservationByID(id));
    }
}
