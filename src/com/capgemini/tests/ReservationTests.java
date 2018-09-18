package com.capgemini.tests;

import com.capgemini.controller.ReservationController;
import com.capgemini.controller.RoomController;
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
    RoomController roomController;
    Guest guest;
    LocalDate startDate;
    LocalDate endDate;
    Room room;
    RoomType roomType;

    @Before
    public void initReservationData() {
        reservationController = new ReservationController();
        roomController = new RoomController();
        roomController.addRoomTypes();

        guest = new Guest();
        startDate = LocalDate.of(2019, 01, 01);
        endDate = LocalDate.of(2019, 02, 01);
        room = new Room();
        roomType = roomController.getRoomType(1);
    }

    @Test
    public void testGetReservationByID() {
        Reservation myReservation = new Reservation(1, startDate, endDate, guest, 6, room, roomType);
        reservationController.addReservation(myReservation);

        Reservation reservation = reservationController.getReservationByID(1);
        assertEquals(myReservation, reservation);
    }

    @Test
    public void testGetReservationByName() {
        guest.setLastName("van de Moosdijk");
        Reservation myReservation = new Reservation(1, startDate, endDate, guest, 6, room, roomType);
        reservationController.addReservation(myReservation);

        Reservation reservation = reservationController.getReservationByName("van de Moosdijk");
        assertEquals(myReservation, reservation);
    }

    @Test
    public void testGetAllReservations() {
        Reservation myReservation1 = new Reservation(1, startDate, endDate, guest, 6, room, roomType);
        Reservation myReservation2 = new Reservation(2, startDate, endDate, guest, 6, room, roomType);
        reservationController.addReservation(myReservation1);
        reservationController.addReservation(myReservation2);

        ArrayList<Reservation> reservationArrayList = new ArrayList<Reservation>();
        reservationArrayList.add(myReservation1);
        reservationArrayList.add(myReservation2);

        assertEquals("test", reservationArrayList, reservationController.getAllReservations());
    }
}
