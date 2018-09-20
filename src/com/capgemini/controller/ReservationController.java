package com.capgemini.controller;

import com.capgemini.domain.Reservation;

import java.util.ArrayList;

public class ReservationController {
    private ArrayList<Reservation> reservations = new ArrayList<>();

    public ArrayList<Reservation> getAllReservations() {
        return reservations;
    }

    public void addReservation(Reservation reservation) {
        reservation.setReservationID((reservations.size() + 1));
        reservations.add(reservation);
    }

    public Reservation getReservationByID(int id) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationID() == id) {
                return reservation;
            }
        }
        return null;
    }

    public Reservation getReservationByName(String lastName) {
        for (Reservation reservation : reservations) {
            if (reservation.getGuest().getLastName() == lastName) {
                return reservation;
            }
        }
        return null;
    }
}

