package com.capgemini;

import com.capgemini.data.RoomTypeRepository;
import com.capgemini.domain.RoomType;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        addRoomTypes();
    }

    public static void addRoomTypes() {
        RoomTypeRepository rtRepo = new RoomTypeRepository();
        ArrayList<RoomType> roomTypeList = new ArrayList<>();
        roomTypeList.add(new RoomType((byte) 2, (byte) 0));
        roomTypeList.add(new RoomType((byte) 4, (byte) 0));
        roomTypeList.add(new RoomType((byte) 6, (byte) 0));
        roomTypeList.add(new RoomType((byte) 0, (byte) 1));
        roomTypeList.add(new RoomType((byte) 0, (byte) 2));
        roomTypeList.add(new RoomType((byte) 0, (byte) 3));
    }
}
