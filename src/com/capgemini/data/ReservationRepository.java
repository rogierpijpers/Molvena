package com.capgemini.data;

import com.capgemini.domain.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {

    private List<Reservation> reservations = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        reservation.setReservationID((reservations.size() + 1));
        reservations.add(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservations;
    }
}
