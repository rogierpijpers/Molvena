package com.capgemini.domain;

import java.util.ArrayList;
import java.util.Date;

public class Reservation {
    private int reservationID;
    private Date startDate;
    private Date endDate;
    private Guest guest;
    private byte amountOfGuests;
    private ArrayList<Room> rooms;
    private boolean checkedIn = false;

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

    public byte getAmountOfGuests() {
        return amountOfGuests;
    }

    public void setAmountOfGuests(byte amountOfGuests) {
        this.amountOfGuests = amountOfGuests;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn, Person loggedInAs) {

        if(loggedInAs instanceof Receptionist){

            // for checkin

            //checks if end date has already past
            if(checkedIn){
                if(new Date().before(getEndDate())){
                    this.checkedIn = checkedIn;
                }
            } else {
                // for checkout
                if(this.checkedIn){
                    System.out.println("You are already checked in");
                } else {
                    System.out.println("You can check out");
                }
            }



        }

    }
}
