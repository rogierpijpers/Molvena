package com.capgemini.domain;

import com.capgemini.exceptions.InvalidDateException;

import javax.persistence.*;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reservationID;
    private Date startDate;
    private Date endDate;
    @ManyToOne
    private Guest guest;
    private int amountOfGuests;
    @ManyToOne(cascade = CascadeType.ALL)
    private Room room;
    private boolean checkedIn;
    private boolean isDeleted;


    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Reservation(){}

    public Reservation(Date startDate, Date endDate, Guest guest,
                       int amountOfGuests, Room room, RoomType roomType){
        this.startDate = startDate;
        this.endDate = endDate;
        this.guest = guest;
        this.amountOfGuests = amountOfGuests;
        this.room = room;
        this.checkedIn = false;
        this.isDeleted = false;
    }

    public long getReservationID() {
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

    public void setAmountOfGuests(int amountOfGuests) {
        this.amountOfGuests = amountOfGuests;
    }

    public Room getRoom() {
        return room;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void checkIn(Date currentDate) throws InvalidDateException {

        if (currentDate.after(getEndDate())) {
            throw new InvalidDateException();
        } else {
            this.checkedIn = true;
        }
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
    public void checkOut(){
        this.checkedIn = false;
    }

    @Override
    public String toString(){
        return startDate.toString() + " - " + endDate.toString() + ". " + guest.getFirstName() + " " + guest.getLastName() + ". " + amountOfGuests;
    }
}
