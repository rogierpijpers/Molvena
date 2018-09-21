package com.capgemini.data;

import com.capgemini.domain.Guest;

import java.util.ArrayList;
import java.util.List;

public class GuestRepository {
    private List<Guest> guests;

    public GuestRepository(){
        guests = new ArrayList<>();
    }

    public List<Guest> getAllGuests(){
        return guests;
    }

    public void addGuest(Guest guest){
        guests.add(guest);
    }

}
