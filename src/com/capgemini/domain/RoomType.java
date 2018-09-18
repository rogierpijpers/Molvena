package com.capgemini.domain;

public class RoomType {
    private int singleBeds;
    private int doubleBeds;

    public RoomType(int singleBeds, int doubleBeds){
        this.singleBeds = singleBeds;
        this.doubleBeds = doubleBeds;
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
