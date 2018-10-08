package com.capgemini.data;

import com.capgemini.domain.Guest;
import com.capgemini.domain.Reservation;
import com.capgemini.domain.Room;
import com.capgemini.domain.RoomType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        if(getReservationById(id) != null){
            int index = reservations.indexOf(getReservationById(id));
            reservations.set(index, reservation);
        }
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
}
