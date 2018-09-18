package com.capgemini.domain;

import com.capgemini.Main;

public class RoomType {
    private int singleBeds;
    private int doubleBeds;

    public RoomType(int singleBeds, int doubleBeds){
        this.singleBeds = singleBeds;
        this.doubleBeds = doubleBeds;
    }

    public void addRoomTypes(){
        Main.addRoomType(new RoomType(2,0));
        Main.addRoomType(new RoomType(4,0));
        Main.addRoomType(new RoomType(6,0));
        Main.addRoomType(new RoomType(0,1));
        Main.addRoomType(new RoomType(0,2));
        Main.addRoomType(new RoomType(0,3));
    }

    // TODO: Create Test
    public int getAmountOfPeople(){
        return singleBeds + (doubleBeds * 2);
    }

    public int getSingleBeds() {
        return singleBeds;
    }

    public void setSingleBeds(byte singleBeds) {
        this.singleBeds = singleBeds;
    }

    public int getDoubleBeds() {
        return doubleBeds;
    }

    public void setDoubleBeds(byte doubleBeds) {
        this.doubleBeds = doubleBeds;
    }
}
