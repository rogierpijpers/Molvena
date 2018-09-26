package com.capgemini.data;

import com.capgemini.domain.Guest;
import com.capgemini.domain.Reservation;
import com.capgemini.domain.Room;
import com.capgemini.domain.RoomType;

import java.util.*;

public class ReservationRepository {

    private List<Reservation> reservations = new ArrayList<>();

    // TODO: Remove after testing
    private static Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);

        return cal.getTime();
    }

    public ReservationRepository(){
        Date startDate = new Date();
        Date endDate = addDays(startDate, 1);
        RoomType roomType1 = new RoomType((byte) 0, (byte) 2);
        Room room1 = new Room();
        room1.setRoomType(roomType1);
        RoomType roomType = room1.getRoomType();

        PersonRepository personRepository = new PersonRepository();
        personRepository.addInitialAccounts();

        Reservation myReservation = new Reservation(startDate, endDate, (Guest) personRepository.getSinglePerson("Jan@vandijk.nl"), 6, room1, roomType);

        addReservation(myReservation);
    }

    public void addReservation(Reservation reservation) {
        reservation.setReservationID((reservations.size() + 1));
        reservations.add(reservation);
    }


    public List<Reservation> getAllReservations() {
        List<Reservation> nonSoftDeletedReservations = new ArrayList<>();
        for (Reservation reservation :
                reservations) {

            if (!reservation.isDeleted()) {
                nonSoftDeletedReservations.add(reservation);
            }
        }
        return nonSoftDeletedReservations;
    }
    public List<Reservation> getAllSoftDeletedReservations() {
        List<Reservation> SoftDeletedReservations = new ArrayList<>();
        for (Reservation reservation :
                reservations) {

            if (reservation.isDeleted()) {
                SoftDeletedReservations.add(reservation);
            }
        }
        return SoftDeletedReservations;
    }
}
