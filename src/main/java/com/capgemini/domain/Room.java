package com.capgemini.domain;

import javax.persistence.*;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roomID;
    @ManyToOne(cascade = CascadeType.MERGE)
    private RoomType roomType;

    public long getRoomID() {
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
