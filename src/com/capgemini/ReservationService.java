package com.capgemini;

import com.capgemini.domain.Reservation;
import com.capgemini.domain.Room;
import jdk.nashorn.internal.ir.LiteralNode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationService {

    public List<Room> getAllRooms() {

        List<Room> roomList = new ArrayList();

        for (int i = 0; i < 5; i++) {
            roomList.add(new Room());
        }
        return roomList;
    }

    public List<Reservation> getAllReservations() {
        List<Reservation> reservationList = new ArrayList();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        for (int i = 0; i < 5; i++) {
            Reservation reservation = new Reservation();
            try {
                Date startdate = dateFormat.parse("19-04-2018");
                Date enddate = dateFormat.parse("25-04-2018");
                reservation.setStartDate(startdate);
                reservation.setEndDate(enddate);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            reservationList.add(new Reservation());
        }
        return reservationList;
    }

    public List<Room> getAllAvailableRooms(Date startDate, Date endDate) {

        List<Room> AvailableRooms = new ArrayList();
        List<Room> AllRooms = getAllRooms();
        List<Reservation> AllReservations = getAllReservations();

        return null;
    }

    private List<Room> getRoomsWithoutReservations() {

        List<Room> AvailableRooms = new ArrayList();
        List<Room> AllRooms = getAllRooms();
        List<Reservation> AllReservations = getAllReservations();
        List<Room> reservedrooms = new ArrayList();

        for (Reservation reservation : AllReservations) {
            for (Room room : reservation.getRooms()) {
                if (!reservedrooms.contains(room))
                    reservedrooms.add(room);
            }
        }
        return null;
    }
}
