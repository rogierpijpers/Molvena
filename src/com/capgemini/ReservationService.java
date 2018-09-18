package com.capgemini;

import com.capgemini.domain.Reservation;
import com.capgemini.domain.Room;
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
        List<Room> availableRooms = new ArrayList();
        List<Room> notAvailableRooms = getRoomsWithReservation(startDate, endDate);
        List<Room> allRooms = getAllRooms();
        for (Room room : allRooms) {
            if (!notAvailableRooms.contains(room)){
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    private List<Room> getRoomsWithReservation(Date startDate, Date endDate) {
        List<Room> allReservedRooms = new ArrayList();

        for (Reservation reservation: getAllReservations()) {
            if (reservation.getEndDate().after(endDate) || reservation.getEndDate().equals(endDate) ||
                    reservation.getStartDate().before(startDate) || reservation.getStartDate().equals(startDate))
                allReservedRooms.addAll(reservation.getRooms());
            }
        return allReservedRooms;
    }
}
