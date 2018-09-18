package com.capgemini.controller;

import com.capgemini.domain.RoomType;

import java.util.ArrayList;

public class RoomController {
    private static ArrayList<RoomType> roomTypeList = new ArrayList<>();

    public static void addRoomType(RoomType type) {
        roomTypeList.add(type);
    }

    public ArrayList<RoomType> getRoomTypeList() {
        return roomTypeList;
    }

    public static void addRoomTypes(){
        roomTypeList.add(new RoomType(2,0));
        roomTypeList.add(new RoomType(4,0));
        roomTypeList.add(new RoomType(6,0));
        roomTypeList.add(new RoomType(0,1));
        roomTypeList.add(new RoomType(0,2));
        roomTypeList.add(new RoomType(0,3));

        for(RoomType roomType : roomTypeList){
            System.out.println(roomType + " has " + roomType.getSingleBeds() + " single beds and " + roomType.getDoubleBeds() + " double beds");
        }
    }
}
