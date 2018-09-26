package com.capgemini.service;

import com.capgemini.data.ReservationRepository;
import com.capgemini.data.RoomRepository;
import com.capgemini.domain.Reservation;
import com.capgemini.domain.Room;
import com.capgemini.domain.RoomType;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private RoomRepository roomRepository;
    private ReservationRepository reservationRepository;

    public void setRoomRepository(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void setReservationRepository(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public ReservationService() {
        roomRepository = new RoomRepository();
        reservationRepository = new ReservationRepository();
    }

    public List<Reservation> getAllReservations(){
        return reservationRepository.getAllReservations();
    }

    public List<Room> getAllAvailableRooms(Date startDate, Date endDate) {
        List<Room> availableRooms = new ArrayList();
        List<Room> notAvailableRooms = getRoomsWithReservation(startDate, endDate);
        List<Room> allRooms = roomRepository.getAllRooms();
        for (Room room : allRooms) {
            if (!notAvailableRooms.contains(room)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public List<Room> getAllAvailableRooms(Date startDate, Date endDate, RoomType roomType) {
        List<Room> availableRooms = getAllAvailableRooms(startDate, endDate);
        List<Room> availableRoomType = new ArrayList();
        for (Room room : availableRooms) {
            if (room.getRoomType().equals(roomType))
                availableRoomType.add(room);
        }
        return availableRoomType;
    }

    private List<Room> getRoomsWithReservation(Date startDate, Date endDate) {
        List<Room> allReservedRooms = new ArrayList();

        for (Reservation reservation : reservationRepository.getAllReservations()) {
            Date reservationStart = reservation.getStartDate();
            Date reservationEnd = reservation.getEndDate();

            boolean checkInputStartDate = (reservationStart.after(startDate) || reservationStart.equals(startDate)) &&
                    (reservationStart.before(endDate) || reservationStart.equals(endDate));

            boolean checkInputEndDate = (reservationEnd.after(startDate) || reservationEnd.equals(startDate)) &&
                    (reservationEnd.before(endDate) || reservationEnd.equals(endDate));

            boolean checkInputRest = (reservationStart.before(startDate) || reservationStart.equals(startDate)) &&
                    (reservationEnd.after(endDate) || reservationEnd.equals(endDate));

            if (checkInputStartDate || checkInputEndDate || checkInputRest)
                allReservedRooms.add(reservation.getRoom());
        }

        return allReservedRooms;

    }

    public void softDelete(Reservation reservation) throws InvalidObjectException{

        if(getReservationByID(reservation.getReservationID()) != null){
            reservation.setDeleted(true);
        } else {
            throw new InvalidObjectException("Reservation not found.");
        }
    }

    public Reservation getReservationByID(int id) {
        for (Reservation reservation : reservationRepository.getAllReservations()) {
            if (reservation.getReservationID() == id) {
                return reservation;
            }
        }
        return null;
    }

    public Reservation getReservationByIdForGuest(int id, String username){
        return getReservationsByUsername(username).stream().filter(x -> x.getReservationID() == id).findFirst().orElse(null);
    }

    public List<Reservation> getReservationsByUsername(String username){
        return reservationRepository.getAllReservations().stream().filter(x -> x.getGuest().getMail().equals(username)).collect(Collectors.toList());
    }

    public Reservation getReservationByName(String lastName) {
        for (Reservation reservation : reservationRepository.getAllReservations()) {
            if (reservation.getGuest().getLastName() == lastName) {
                return reservation;
            }
        }
        return null;
    }

    public void addReservation(Reservation reservation){
        reservationRepository.addReservation(reservation);
    }
}
