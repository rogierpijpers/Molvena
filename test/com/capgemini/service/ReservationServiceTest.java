package com.capgemini.service;

import com.capgemini.domain.Guest;
import com.capgemini.domain.Reservation;
import com.capgemini.domain.Room;
import com.capgemini.domain.RoomType;
import org.junit.BeforeClass;
import com.capgemini.data.RoomRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationServiceTest {

    private ReservationService reservationService;
    private RoomRepository roomRepository;

    private Room room1 = new Room();
    private Room room2 = new Room();

    @BeforeClass
    public void setupRoom() {
       roomRepository = new RoomRepository();

        RoomType roomType1 = new RoomType();
        roomType1.setSingleBeds((byte)0);
        roomType1.setDoubleBeds((byte)2);
        room1.setRoomID((short)1);
        room1.setRoomType(roomType1);

        RoomType roomType2 = new RoomType();
        roomType2.setSingleBeds((byte)2);
        roomType2.setDoubleBeds((byte)0);
        room2.setRoomID((short)1);
        room2.setRoomType(roomType2);

        roomRepository.addRoom(room1);
        roomRepository.addRoom(room2);

        //make two repositories
        //create service and repository to service
        //Make two tests in seperate method
    }

    public void setupReservation(){
        reservationService = new ReservationService();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {

        Reservation reservation1 = new Reservation();
        reservation1.setReservationID(1);
        Date startDateReservation1 = dateFormat.parse("19-04-2018");
        Date endDateReservation1 = dateFormat.parse("25-04-2018");
        reservation1.setStartDate(startDateReservation1);
        reservation1.setEndDate(endDateReservation1);
        reservation1.setAmountOfGuests((byte)2);
        Guest guest1 = new Guest();
        Guest guest2 = new Guest();
        guest1.setAddress("Oudenoord 363");
        guest1.setCity("Utrecht");
        guest1.setCountry("Holland");
        Date guest1Date = dateFormat.parse("19-04-1991");
        guest1.setDateOfBirth(guest1Date);
        guest1.setFirstName("Jeroen");
        guest1.setLastName("Grift");
        guest1.setMail("jeroengrift@haha.nl");
        guest1.setPassword("test");
        guest1.setPhone("112");
        guest1.setZipCode("1111 XX");
        guest2.setAddress("Dijk 1");
        guest2.setCity("Lutjebroek");
        guest2.setCountry("Holland");
        Date guest2Date = dateFormat.parse("01-12-1990");
        guest2.setDateOfBirth(guest2Date);
        guest2.setFirstName("Henk");
        guest2.setLastName("Blik");
        guest2.setMail("henkblik@haha.nl");
        guest2.setPassword("blik");
        guest2.setPhone("00083728");
        guest2.setZipCode("6363 EE");
        reservation1.setGuest(guest1);
        reservation1.setGuest(guest2);
        reservation1.setCheckedIn(true);
        List<Room> roomList = new ArrayList();
        roomList.add(room1);
        roomList.add(room2);
        reservation1.setRooms((ArrayList<Room>) roomList);


        }

        catch (ParseException e) {
        e.printStackTrace();
        }
    }
}