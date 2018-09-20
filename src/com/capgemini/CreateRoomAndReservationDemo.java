package com.capgemini;

import com.capgemini.controller.ReservationController;
import com.capgemini.controller.RoomTypeController;
import com.capgemini.domain.Guest;
import com.capgemini.domain.Reservation;
import com.capgemini.domain.Room;
import com.capgemini.domain.RoomType;

import java.time.LocalDate;

public class CreateRoomAndReservationDemo {
    public void runDemo() {

        // How to initialize the hard-coded Room Types.
        RoomTypeController roomTypeController = new RoomTypeController();
        ReservationController reservationController = new ReservationController();

        roomTypeController.addRoomTypes();

        // Mock data to create a reservation.
        Guest guest = new Guest();
        LocalDate startDate = LocalDate.of(2019, 01, 01);
        LocalDate endDate = LocalDate.of(2019, 02, 01);
        Room room = new Room();
        RoomType roomType = roomTypeController.getRoomType(1);

        // Create the reservation
        Reservation reservation = new Reservation(startDate, endDate, guest, 6, room, roomType);
        reservationController.addReservation(reservation);

        // View all reservations
        System.out.println("Printing all reservations: " + reservationController.getAllReservations());

        // Get reservation by ID
        System.out.println("Searching Reservation by given ID (1): " + reservationController.getReservationByID(reservation.getReservationID()));
    }
}
