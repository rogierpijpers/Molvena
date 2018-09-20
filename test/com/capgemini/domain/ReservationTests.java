package com.capgemini.domain;

import com.capgemini.data.ReservationRepository;
import com.capgemini.data.RoomTypeRepository;
import com.capgemini.domain.Guest;
import com.capgemini.domain.Reservation;
import com.capgemini.domain.Room;
import com.capgemini.domain.RoomType;
import com.capgemini.service.ReservationService;
import com.capgemini.service.RoomTypeService;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ReservationTests {
    ReservationRepository reservationRepository;
    ReservationService reservationService;
    RoomTypeRepository roomTypeRepository;
    RoomTypeService roomTypeService;
    Guest guest;
    Date startDate;
    Date endDate;
    Room room;
    RoomType roomType;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


    @Before
    public void initReservationData() {
        reservationRepository = new ReservationRepository();
        roomTypeRepository = new RoomTypeRepository();
        reservationService = new ReservationService();
        reservationService.setReservationRepository(reservationRepository);

        guest = new Guest();

        try {
            startDate = dateFormat.parse("19-04-2018");
            endDate = dateFormat.parse("19-05-2018");
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        room = new Room();

        roomTypeRepository.addRoomType(new RoomType((byte) 2, (byte) 0));
        roomTypeRepository.addRoomType(new RoomType((byte) 4, (byte) 0));
        roomTypeRepository.addRoomType(new RoomType((byte) 6, (byte) 0));
        roomTypeRepository.addRoomType(new RoomType((byte) 2, (byte) 1));
        roomTypeRepository.addRoomType(new RoomType((byte) 2, (byte) 2));
        roomTypeRepository.addRoomType(new RoomType((byte) 2, (byte) 3));
        roomType = roomTypeRepository.getRoomType(1);
    }

    @Test
    public void testReservationConstructor() {
        Reservation myReservation = new Reservation(startDate, endDate, guest, 6, room, roomType);
        reservationRepository.addReservation(myReservation);

        Reservation reservation = reservationService.getReservationByID(1);

        // Test if the submitted parameters doesn't get corrupted along the way.
        assertEquals(myReservation, reservation);
    }

    @Test
    public void testGetReservationByID() {
        Reservation myReservation = new Reservation(startDate, endDate, guest, 6, room, roomType);
        reservationRepository.addReservation(myReservation);

        Reservation reservation = reservationService.getReservationByID(1);
        // if(myReservation.equals(reservation))
        // Tests if the correct reservation returns correctly by GetReservationID();
        assertEquals(myReservation.getReservationID(), reservation.getReservationID());
    }

    @Test
    public void testGetReservationByName() {
        guest.setLastName("van de Moosdijk");
        Reservation myReservation = new Reservation(startDate, endDate, guest, 6, room, roomType);
        reservationRepository.addReservation(myReservation);

        reservationRepository.addReservation(myReservation);

        Reservation reservation = reservationService.getReservationByName("van de Moosdijk");
        // Tests if the right reservation returns when using GetReservationByName();
        assertEquals(myReservation.getGuest().getLastName(), reservation.getGuest().getLastName());
    }

    @Test
    public void testGetAllReservations() {
        Reservation myReservation1 = new Reservation(startDate, endDate, guest, 6, room, roomType);
        Reservation myReservation2 = new Reservation(startDate, endDate, guest, 6, room, roomType);

        reservationRepository.addReservation(myReservation1);
        reservationRepository.addReservation(myReservation2);

        ArrayList<Reservation> reservationArrayList = new ArrayList<>();
        reservationArrayList.add(myReservation1);
        reservationArrayList.add(myReservation2);

        // Test if the array object itself is equal.
        assertEquals(reservationArrayList, reservationRepository.getAllReservations());
        // Test if the size of the arrays are equal (is this necessary?)
        assertEquals((reservationArrayList.size()), reservationRepository.getAllReservations().size());
    }
}
