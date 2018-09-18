package com.capgemini.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Reservation {
    private int reservationID;
    private LocalDate startDate;
    private LocalDate endDate;
    private Guest guest;
    private int amountOfGuests;
    private Room rooms;
    private RoomType roomType;

    private boolean checkedIn = false;

    public Reservation(int reservationID, LocalDate startDate, LocalDate endDate, Guest guest,
                       int amountOfGuests, Room rooms, RoomType roomType) {
        this.reservationID = reservationID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guest = guest;
        this.amountOfGuests = amountOfGuests;
        this.rooms = rooms;
        this.roomType = roomType;
    }

    // TODO: Test
    public void checkIn() {

    }

    // TODO: Test
    public void checkOut() {

    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public int getAmountOfGuests() {
        return amountOfGuests;
    }

    public void setAmountOfGuests(byte amountOfGuests) {
        this.amountOfGuests = amountOfGuests;
    }

    public Room getRooms() {
        return rooms;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }
}
