package com.capgemini.data;

import com.capgemini.domain.Guest;
import com.capgemini.domain.Reservation;
import com.capgemini.domain.Room;
import com.capgemini.domain.RoomType;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ReservationRepository {

    private List<Reservation> reservations;

    public ReservationRepository(){
        reservations = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        reservation.setReservationID((reservations.size() + 1));
        reservations.add(reservation);
    }

    public Reservation getReservationById(int id){
        return getAllReservations().stream().filter(x -> x.getReservationID() == id).findFirst().orElse(null);
    }

    public void updateReservation(int id, Reservation reservation){
        Reservation replace = getReservationById(id);
        reservations.remove(replace);
        reservations.add(reservation);
    }

    public List<Reservation> getAllReservations() {
        List<Reservation> nonSoftDeletedReservations = new ArrayList<>();
        for (Reservation reservation :
                reservations) {

            if (!reservation.isDeleted()) {
                nonSoftDeletedReservations.add(reservation);
            }
        }
        return nonSoftDeletedReservations;
    }
    public List<Reservation> getAllSoftDeletedReservations() {
        List<Reservation> SoftDeletedReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.isDeleted()) {
                SoftDeletedReservations.add(reservation);
            }
        }
        return SoftDeletedReservations;
    }

    //Get a reservation by id
    public Reservation getReservationById(int reservationID) {
        List<Reservation> allReservations = getAllReservations();
        for (Reservation reservation :  allReservations) {
            if (reservation.getReservationID() == reservationID)
                return reservation;
        }
        return null;
    }

    //Give the id of the reservation you wan to update and the new reservation object you want to update
    public void updateReservation(int reservationID, Reservation currentReservations){
        //find the reservation by its ID
        Reservation reservation = getReservationById(reservationID);
        //find the index of the reservation in the list
        int reservationIndex = reservations.indexOf(reservation);
        //update the reservation
        reservations.set(reservationIndex , currentReservations);
    }
}
