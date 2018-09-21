package com.capgemini.demo;

import com.capgemini.domain.Guest;
import com.capgemini.domain.Person;
import com.capgemini.domain.Reservation;
import com.capgemini.exceptions.InvalidDateException;

import java.util.Date;
import java.util.Scanner;

public class CheckInDemo extends Demo {
    private Person currentUser;
    private Scanner inputReader;

    public CheckInDemo(Person currentUser, Scanner inputReader){
        this.currentUser = currentUser;
        this.inputReader = inputReader;
    }

    @Override
    public void run() {
        initTestData();

        if(currentUser instanceof Guest){
            System.out.println("You are not authorised to perform this action");
            return;
        }

        System.out.println("Please select a reservation to check in");
        int i = 0;
        for(Reservation reservation : reservationRepository.getAllReservations())
            System.out.println(i + ". " + reservation); i++;

        int reservationIndex = inputReader.nextInt();
        Reservation reservation = reservationRepository.getAllReservations().get(reservationIndex);

        try {
            reservation.checkIn(new Date());
            System.out.println("Check in successful");
            System.out.println(reservation);
        } catch (InvalidDateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "Check in";
    }
}
