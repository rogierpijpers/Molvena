package com.capgemini.controller;

import com.capgemini.domain.RoomType;

import java.util.ArrayList;

public class RoomController {
    private ArrayList<RoomType> roomTypeList = new ArrayList<>();

    public void addRoomType(RoomType type) {
        roomTypeList.add(type);
    }

    public RoomType getRoomType(int n) {
        return roomTypeList.get(n);
    }

    public byte getAmountOfPeople(byte singleBeds, byte doubleBeds){
        byte people = (byte)(singleBeds + (doubleBeds * 2));
        return people;
    }
    
    public void addRoomTypes() {
        roomTypeList.add(new RoomType((byte) 2, (byte) 0));
        roomTypeList.add(new RoomType((byte) 4, (byte) 0));
        roomTypeList.add(new RoomType((byte) 6, (byte) 0));
        roomTypeList.add(new RoomType((byte) 0, (byte) 1));
        roomTypeList.add(new RoomType((byte) 0, (byte) 2));
        roomTypeList.add(new RoomType((byte) 0, (byte) 3));

        for (RoomType roomType : roomTypeList) {
            System.out.println(roomType + " has " + roomType.getSingleBeds() + " single beds and " + roomType.getDoubleBeds() + " double beds.");
        }
    }
}
