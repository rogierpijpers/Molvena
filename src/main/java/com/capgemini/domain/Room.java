package com.capgemini.domain;

public class Room {
    private short roomID;
    private RoomType roomType;

    public short getRoomID() {
        return roomID;
    }

    public void setRoomID(short roomID) {
        this.roomID = roomID;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public String toString(){
        return roomID + " " + roomType.getDoubleBeds() + " - " + roomType.getSingleBeds();
    }
}

