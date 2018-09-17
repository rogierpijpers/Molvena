package com.capgemini.domain;

public class RoomType {
    private byte singleBeds;
    private byte doubleBeds;

    // TODO: Create Test
    public int getAmountOfPeople(){
        return singleBeds + (doubleBeds * 2);
    }

    public byte getSingleBeds() {
        return singleBeds;
    }

    public void setSingleBeds(byte singleBeds) {
        this.singleBeds = singleBeds;
    }

    public byte getDoubleBeds() {
        return doubleBeds;
    }

    public void setDoubleBeds(byte doubleBeds) {
        this.doubleBeds = doubleBeds;
    }
}
