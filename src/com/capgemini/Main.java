package com.capgemini;

import com.capgemini.data.RoomTypeRepository;
import com.capgemini.domain.RoomType;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        addRoomTypes();
    }

    public static void addRoomTypes() {
        RoomTypeRepository roomTypeRepository = new RoomTypeRepository();
        roomTypeRepository.addRoomType(new RoomType((byte) 2, (byte) 0));
        roomTypeRepository.addRoomType(new RoomType((byte) 4, (byte) 0));
        roomTypeRepository.addRoomType(new RoomType((byte) 6, (byte) 0));
        roomTypeRepository.addRoomType(new RoomType((byte) 2, (byte) 1));
        roomTypeRepository.addRoomType(new RoomType((byte) 2, (byte) 2));
        roomTypeRepository.addRoomType(new RoomType((byte) 2, (byte) 3));
    }
}
