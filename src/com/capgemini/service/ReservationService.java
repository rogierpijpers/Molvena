package com.capgemini.service;

import com.capgemini.data.ReservationRepository;
import com.capgemini.data.RoomRepository;
import com.capgemini.domain.Reservation;
import com.capgemini.domain.Room;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationService {

    private RoomRepository roomRepository;
    private ReservationRepository reservationRepository;

    public ReservationService() {
        roomRepository = new RoomRepository();
        reservationRepository = new ReservationRepository();
    }

    public List<Room> getAllAvailableRooms(Date startDate, Date endDate) {
        List<Room> availableRooms = new ArrayList();
        List<Room> notAvailableRooms = getRoomsWithReservation(startDate, endDate);
        List<Room> allRooms = roomRepository.getAllRooms();
        for (Room room : allRooms) {
            if (!notAvailableRooms.contains(room)){
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    private List<Room> getRoomsWithReservation(Date startDate, Date endDate) {
        List<Room> allReservedRooms = new ArrayList();

        for (Reservation reservation: reservationRepository.getAllReservations()) {
            if (reservation.getEndDate().after(endDate) || reservation.getEndDate().equals(endDate) ||
                    reservation.getStartDate().before(startDate) || reservation.getStartDate().equals(startDate))
                allReservedRooms.addAll(reservation.getRooms());
            }
        return allReservedRooms;
    }
}
