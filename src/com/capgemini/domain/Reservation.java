package com.capgemini.domain;

import java.util.Date;

public class Reservation {
    private int reservationID;
    private Date startDate;
    private Date endDate;
    private Guest guest;
    private int amountOfGuests;
    private Room room;
    private RoomType roomType;
    private boolean checkedIn;
    private boolean isDeleted;

    public Reservation(Date startDate, Date endDate, Guest guest,
                       int amountOfGuests, Room room, RoomType roomType){
        this.startDate = startDate;
        this.endDate = endDate;
        this.guest = guest;
        this.amountOfGuests = amountOfGuests;
        this.room = room;
        this.roomType = roomType;
        this.checkedIn = false;
        this.isDeleted = false;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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

    public Room getRoom() {
        return room;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public boolean equals(Object obj) {
        // if instanceof reservation
        if (!(obj instanceof Reservation))
            return false;

        Reservation reservation = (Reservation)obj;

        if(this.getReservationID() != reservation.getReservationID())
            return false;

        if (this.getStartDate() != reservation.getStartDate() )
            return false;

        if (this.getEndDate() != reservation.getEndDate())
            return false;

        if (this.getAmountOfGuests() != reservation.getAmountOfGuests())
            return false;

        if (this.getGuest() != reservation.getGuest())
            return false;

        if (this.getRoom() != reservation.getRoom())
            return false;

        if (this.getRoom().getRoomType() != reservation.getRoom().getRoomType())
            return false;

        return true;
    }
}
