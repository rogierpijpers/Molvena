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

    @Override
    public boolean equals(Object object) {
       if (!(object instanceof RoomType))
           return false;
       RoomType roomType = (RoomType)object;
       return (roomType.getSingleBeds() == this.getSingleBeds() && roomType.getDoubleBeds() == this.getDoubleBeds());
    }
}
