package com.capgemini.demo;

import com.capgemini.domain.Person;
import com.capgemini.domain.Reservation;

import java.io.InvalidObjectException;
import java.util.Scanner;

public class CancelReservationDemo extends Demo {
    private Person currentUser;
    private Scanner inputReader;

    public CancelReservationDemo(Person currentUser, Scanner inputReader){
        this.currentUser = currentUser;
        this.inputReader = inputReader;
    }

    @Override
    public void run() {
        initTestData();

        System.out.println("Please select a reservation to cancel");
        int i = 0;
        for(Reservation reservation : reservationRepository.getAllReservations())
            System.out.println(i + ". " + reservation); i++;

        int reservationIndex = inputReader.nextInt();
        Reservation reservation = reservationRepository.getAllReservations().get(reservationIndex);

        try {
            reservationService.softDelete(reservation);
            System.out.println("Reservation deleted: " + reservation.isDeleted());

            System.out.println("Active reservations:");
            for(Reservation res : reservationRepository.getAllReservations())
                System.out.println(i + ". " + res);

        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "Cancel reservation";
    }
}
