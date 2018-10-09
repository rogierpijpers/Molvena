package com.capgemini.domain;

public class RoomType {
    private byte singleBeds;
    private byte doubleBeds;

    public RoomType(){}

    public RoomType(byte singleBeds, byte doubleBeds){
        this.singleBeds = singleBeds;
        this.doubleBeds = doubleBeds;
    }

    public String getName() {
        String name = "";

        if(singleBeds == 0){
        } else if(singleBeds == 1) {
            name += "one single bed";
        } else {
            name += singleBeds + " single beds";
        }

        if(singleBeds > 0 && doubleBeds > 0){
            name += " and ";

            if(doubleBeds == 1){
                name += "one double bed";
            } else {
                name += doubleBeds + " double beds";
            }
        } else {
            name += doubleBeds + " double beds";
        }

        return name;
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
