package com.capgemini.tests;

import com.capgemini.controller.ReservationController;
import com.capgemini.controller.RoomTypeController;
import com.capgemini.domain.Guest;
import com.capgemini.domain.Reservation;
import com.capgemini.domain.Room;
import com.capgemini.domain.RoomType;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ReservationTests {
    ReservationController reservationController;
    RoomTypeController roomTypeController;
    Guest guest;
    LocalDate startDate;
    LocalDate endDate;
    Room room;
    RoomType roomType;

    @Before
    public void initReservationData() {
        reservationController = new ReservationController();
        roomTypeController = new RoomTypeController();
        roomTypeController.addRoomTypes();

        guest = new Guest();
        startDate = LocalDate.of(2019, 01, 01);
        endDate = LocalDate.of(2019, 02, 01);
        room = new Room();
        roomType = roomTypeController.getRoomType(1);
    }

    @Test
    public void testReservationConstructor() {
        Reservation myReservation = new Reservation(startDate, endDate, guest, 6, room, roomType);
        reservationController.addReservation(myReservation);

        Reservation reservation = reservationController.getReservationByID(1);

        // Test if the submitted parameters doesn't get corrupted along the way.
        assertEquals(myReservation, reservation);
    }

    @Test
    public void testGetReservationByID() {
        Reservation myReservation = new Reservation(startDate, endDate, guest, 6, room, roomType);
        reservationController.addReservation(myReservation);

        Reservation reservation = reservationController.getReservationByID(1);
        // if(myReservation.equals(reservation))
        // Tests if the correct reservation returns correctly by GetReservationID();
        assertEquals(myReservation.getReservationID(), reservation.getReservationID());
    }

    @Test
    public void testGetReservationByName() {
        guest.setLastName("van de Moosdijk");
        Reservation myReservation = new Reservation(startDate, endDate, guest, 6, room, roomType);
        reservationController.addReservation(myReservation);

        reservationController.addReservation(myReservation);

        Reservation reservation = reservationController.getReservationByName("van de Moosdijk");
        // Tests if the right reservation returns when using GetReservationByName();
        assertEquals(myReservation.getGuest().getLastName(), reservation.getGuest().getLastName());
    }

    @Test
    public void testGetAllReservations() {
        Reservation myReservation1 = new Reservation(startDate, endDate, guest, 6, room, roomType);
        Reservation myReservation2 = new Reservation(startDate, endDate, guest, 6, room, roomType);


        reservationController.addReservation(myReservation1);
        reservationController.addReservation(myReservation2);

        ArrayList<Reservation> reservationArrayList = new ArrayList<>();
        reservationArrayList.add(myReservation1);
        reservationArrayList.add(myReservation2);

        // Test if the array object itself is equal.
        assertEquals(reservationArrayList, reservationController.getAllReservations());
        // Test if the size of the arrays are equal (is this necessary?)
        assertEquals((reservationArrayList.size()), reservationController.getAllReservations().size());
    }
}
