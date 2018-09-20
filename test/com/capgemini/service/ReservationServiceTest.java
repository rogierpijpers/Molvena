package com.capgemini.service;

import com.capgemini.data.ReservationRepository;
import com.capgemini.domain.Guest;
import com.capgemini.domain.Reservation;
import com.capgemini.domain.Room;
import com.capgemini.domain.RoomType;
import com.capgemini.service.ReservationService;
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

    private RoomType roomType1;

    @Before
    public void setupRoom() {
        roomRepository = new RoomRepository();

        roomType1 = new RoomType((byte) 0, (byte) 2);
        room1.setRoomType(roomType1);

        roomRepository.addRoom(room1);
    }

    @Before
    public void setupReservation() {
        reservationRepository = new ReservationRepository();
        Date startDateReservation1 = null;
        Date endDateReservation1 = null;
        try {
            startDateReservation1 = dateFormat.parse("19-04-2018");
            endDateReservation1 = dateFormat.parse("25-04-2018");
            Guest guest = new Guest();

            Reservation reservation = new Reservation(startDateReservation1, endDateReservation1, guest, 6, room1, roomType1);

            reservationRepository.addReservation(reservation);
        }
         catch(ParseException e){
                e.printStackTrace();
            }
        }

        @Test
        public void getAvailableRoomsNotAvailable () {
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
        public void getAvailableRoomsAvailable () {
            try {

                Date startDateReservation = dateFormat.parse("16-04-2018");
                Date endDateReservation = dateFormat.parse("18-04-2018");

                ReservationService reservationService = new ReservationService();
                reservationService.setReservationRepository(reservationRepository);
                reservationService.setRoomRepository(roomRepository);
                List<Room> result = reservationService.getAllAvailableRooms(startDateReservation, endDateReservation);

                //comparison, expected = 2 rooms left. compare with size of results
                Assert.assertSame(1, result.size());

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        @Test
        public void getAvailableRoomsNotAvailableEndDateInside () {
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
        public void getAvailableRoomsNotAvailableStartDateInside () {
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
        public void getAvailableRoomsWithRoomType () {
            try {

                Date startDateReservation = dateFormat.parse("16-04-2018");
                Date endDateReservation = dateFormat.parse("18-04-2018");

                RoomType roomTypeTest = new RoomType((byte)0, (byte)2);

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
        public void getAvailableRoomsWithWrongRoomType () {
            try {

                Date startDateReservation = dateFormat.parse("16-04-2018");
                Date endDateReservation = dateFormat.parse("18-04-2018");

                RoomType roomTypeTest = new RoomType((byte)0, (byte)3);

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
