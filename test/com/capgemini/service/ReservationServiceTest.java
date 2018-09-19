package com.capgemini.service;

import com.capgemini.data.ReservationRepository;
import com.capgemini.domain.Reservation;
import com.capgemini.domain.Room;
import com.capgemini.domain.RoomType;
import org.junit.Assert;
import org.junit.Before;
import com.capgemini.data.RoomRepository;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationServiceTest {

    private ReservationRepository reservationRepository;
    private RoomRepository roomRepository;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    private Room room1 = new Room();
    private Room room2 = new Room();

    @Before
    public void setupRoom() {
        roomRepository = new RoomRepository();

        RoomType roomType1 = new RoomType();
        roomType1.setSingleBeds((byte) 0);
        roomType1.setDoubleBeds((byte) 2);
        room1.setRoomID((short) 1);
        room1.setRoomType(roomType1);

        RoomType roomType2 = new RoomType();
        roomType2.setSingleBeds((byte) 2);
        roomType2.setDoubleBeds((byte) 0);
        room2.setRoomID((short) 1);
        room2.setRoomType(roomType2);

        roomRepository.addRoom(room1);
        roomRepository.addRoom(room2);
    }

    @Before
    public void setupReservation() {
        reservationRepository = new ReservationRepository();
        Reservation reservation1 = new Reservation();
        reservation1.setReservationID(1);

        try {

            Date startDateReservation1 = dateFormat.parse("19-04-2018");
            Date endDateReservation1 = dateFormat.parse("25-04-2018");
            reservation1.setStartDate(startDateReservation1);
            reservation1.setEndDate(endDateReservation1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        reservation1.setAmountOfGuests((byte) 2);
        reservation1.setGuest(null);
        reservation1.setGuest(null);
        reservation1.setCheckedIn(true);

        List<Room> roomList = new ArrayList();
        roomList.add(room1);
        roomList.add(room2);
        reservation1.setRooms((ArrayList<Room>) roomList);

        reservationRepository.addReservation(reservation1);
    }

    @Test
    public void getAvailableRoomsWithinDateParametersTest() {
        try {

            Date startDateReservation = dateFormat.parse("20-04-2018");
            Date endDateReservation = dateFormat.parse("24-04-2018");

            ReservationService reservationService = new ReservationService();
            reservationService.setReservationRepository(reservationRepository);
            reservationService.setRoomRepository(roomRepository);
            List<Room> result = reservationService.getAllAvailableRooms(startDateReservation, endDateReservation);

            //comparison, expected = 0 rooms left. compare with size of results
            Assert.assertSame(0, result.size());

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAvailableRoomsOutsideDateParametersTest() {
        try {

            Date startDateReservation = dateFormat.parse("16-04-2018");
            Date endDateReservation = dateFormat.parse("18-04-2018");

            ReservationService reservationService = new ReservationService();
            reservationService.setReservationRepository(reservationRepository);
            reservationService.setRoomRepository(roomRepository);
            List<Room> result = reservationService.getAllAvailableRooms(startDateReservation, endDateReservation);

            //comparison, expected = 2 rooms left. compare with size of results
            Assert.assertSame(2, result.size());

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAvailableRoomsInsideStartBeforeDateParametersTest() {
        try {

            Date startDateReservation = dateFormat.parse("18-04-2018");
            Date endDateReservation = dateFormat.parse("23-04-2018");

            ReservationService reservationService = new ReservationService();
            reservationService.setReservationRepository(reservationRepository);
            reservationService.setRoomRepository(roomRepository);
            List<Room> result = reservationService.getAllAvailableRooms(startDateReservation, endDateReservation);

            //comparison, expected = 0 rooms left. compare with size of results
            Assert.assertSame(0, result.size());

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAvailableRoomsInsideEndAfterDateParametersTest() {
        try {

            Date startDateReservation = dateFormat.parse("23-04-2018");
            Date endDateReservation = dateFormat.parse("28-04-2018");

            ReservationService reservationService = new ReservationService();
            reservationService.setReservationRepository(reservationRepository);
            reservationService.setRoomRepository(roomRepository);
            List<Room> result = reservationService.getAllAvailableRooms(startDateReservation, endDateReservation);

            //comparison, expected = 0 rooms left. compare with size of results
            Assert.assertSame(0, result.size());

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAvailableRoomsWithRoomTypeOutside() {
        try {

            Date startDateReservation = dateFormat.parse("16-04-2018");
            Date endDateReservation = dateFormat.parse("18-04-2018");

            RoomType roomTypeTest = new RoomType();
            roomTypeTest.setSingleBeds((byte) 0);
            roomTypeTest.setDoubleBeds((byte) 2);

            ReservationService reservationService = new ReservationService();
            reservationService.setReservationRepository(reservationRepository);
            reservationService.setRoomRepository(roomRepository);

            List<Room> result = reservationService.getAllAvailableRooms(startDateReservation, endDateReservation, roomTypeTest);

            //comparison, expected = 1 rooms left. compare with size of results
            Assert.assertSame(1, result.size());

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAvailableRoomsWithWrongRoomTypeOutside() {
        try {

            Date startDateReservation = dateFormat.parse("16-04-2018");
            Date endDateReservation = dateFormat.parse("18-04-2018");

            RoomType roomTypeTest = new RoomType();
            roomTypeTest.setSingleBeds((byte) 0);
            roomTypeTest.setDoubleBeds((byte) 3);

            ReservationService reservationService = new ReservationService();
            reservationService.setReservationRepository(reservationRepository);
            reservationService.setRoomRepository(roomRepository);

            List<Room> result = reservationService.getAllAvailableRooms(startDateReservation, endDateReservation, roomTypeTest);

            //comparison, expected = 0 rooms left. compare with size of results
            Assert.assertSame(0, result.size());

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
