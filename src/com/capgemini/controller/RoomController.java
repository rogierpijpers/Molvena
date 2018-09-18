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

    public void addRoomTypes() {
        roomTypeList.add(new RoomType(2, 0));
        roomTypeList.add(new RoomType(4, 0));
        roomTypeList.add(new RoomType(6, 0));
        roomTypeList.add(new RoomType(0, 1));
        roomTypeList.add(new RoomType(0, 2));
        roomTypeList.add(new RoomType(0, 3));

        for (RoomType roomType : roomTypeList) {
            System.out.println(roomType + " has " + roomType.getSingleBeds() + " single beds and " + roomType.getDoubleBeds() + " double beds.");
        }
    }
}
