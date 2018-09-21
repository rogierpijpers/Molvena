package com.capgemini.data;

import com.capgemini.domain.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {

    private List<Reservation> reservations = new ArrayList<>();
    private List<Reservation> nonSoftDeletedReservations = new ArrayList<>();
    private List<Reservation> SoftDeletedReservations = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        reservation.setReservationID((reservations.size() + 1));
        reservations.add(reservation);
    }


    public List<Reservation> getAllReservations() {
        for (Reservation reservation :
                reservations) {

            if (!reservation.isDeleted()) {
                nonSoftDeletedReservations.add(reservation);
            }
        }
        return nonSoftDeletedReservations;
    }
    public List<Reservation> getAllSoftDeletedReservations() {
        for (Reservation reservation :
                reservations) {

            if (reservation.isDeleted()) {
                SoftDeletedReservations.add(reservation);
            }
        }
        return SoftDeletedReservations;
    }

}
