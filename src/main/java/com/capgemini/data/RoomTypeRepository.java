package com.capgemini.data;

import com.capgemini.domain.RoomType;

import java.util.ArrayList;

public class RoomTypeRepository {

    private ArrayList<RoomType> roomTypeList = new ArrayList<>();
    public void addRoomType(RoomType type) {
        roomTypeList.add(type);
    }

    public RoomType getRoomType(int n) {
        return roomTypeList.get(n);
    }
    // TODO getAllRoomTypes return list

}
