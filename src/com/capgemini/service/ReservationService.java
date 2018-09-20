package com.capgemini.service;

import com.capgemini.data.ReservationRepository;
import com.capgemini.data.RoomRepository;
import com.capgemini.domain.Person;
import com.capgemini.domain.Reservation;
import com.capgemini.domain.Room;
import com.capgemini.domain.RoomType;
import java.util.*;

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

        for(Reservation reservation : reservationRepository.getAllReservations()){
            Date reservationStart = reservation.getStartDate();
            Date reservationEnd = reservation.getEndDate();

            boolean checkInputStartDate = (reservationStart.after(startDate) || reservationStart.equals(startDate)) &&
                    (reservationStart.before(endDate) || reservationStart.equals(endDate));

            boolean checkInputEndDate = (reservationEnd.after(startDate) || reservationEnd.equals(startDate)) &&
                    (reservationEnd.before(endDate) || reservationEnd.equals(endDate));

            boolean checkInputRest = (reservationStart.before(startDate) || reservationStart.equals(startDate)) &&
                    (reservationEnd.after(endDate) || reservationEnd.equals(endDate));

            if(checkInputStartDate || checkInputEndDate || checkInputRest)
                allReservedRooms.addAll(reservation.getRooms());
        }
        return allReservedRooms;

    }


//    private void setSoftDelete(String lastName){
//
//        //All the rooms will be stored in this variable
//        List<Room> lastnameResult = new ArrayList();
//
//        //For each reservation in the repository, do the following...
//        for(Reservation reservation : reservationRepository.getAllReservations()){
//
//            //store the last name of the reservation into this variable
//            String reservationLastName = reservation.getGuest().getLastName();
//
//            //if last name equals the reservations last name
//            if(lastName.equals(reservationLastName)){
//                if(reservation.isDeleted()){
//                    System.out.println("This reservation is already deleted");
//                } else {
//                    lastnameResult.addAll(reservation.getRooms());
//                }
//            } else {
//                System.out.println("No reservations found");
//            }
//        }
//    }

    private void setSoftDelete(int reservationID){

        //For each reservation in the repository, do the following...
        for(Reservation reservation : reservationRepository.getAllReservations()){

            //store the reservation ID of the reservation into this variable
            int ID = reservation.getReservationID();

            //if last name equals the reservations last name
            if(reservationID == ID){
                reservation.setDeleted(true);
            } else {
                System.out.println("No reservations found");
            }
        }
    }
}
