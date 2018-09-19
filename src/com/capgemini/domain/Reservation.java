package com.capgemini.domain;

import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public void setCheckedIn(boolean checkedIn, Date currentDate) {
        if(this.checkedIn){
            System.out.println("You are already checked in, do you want to check-out?");
            if(checkedIn){
                System.out.println("You are already checked in");
            } else {
                System.out.println("You are checked out");
                this.checkedIn = checkedIn;
            }
        } else {
            System.out.println("Do you want to check-in?");
            if(checkedIn){
                System.out.println("You can check-in");
                if(currentDate.before(endDate)){
                    System.out.println("You are checked in");
                    this.checkedIn = checkedIn;
                } else {
                    System.out.println("You can't check-in after the end-date");
                }
            } else {
                System.out.println("You can't check-out, please check-in first");
            }
        }
    }
}
