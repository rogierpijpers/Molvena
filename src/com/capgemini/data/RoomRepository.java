package com.capgemini.data;

import com.capgemini.domain.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomRepository {
    public List<Room> getAllRooms() {
        List<Room> roomList = new ArrayList();
        for (int i = 0; i < 5; i++) {
            roomList.add(new Room());
        }
        return roomList;
    }

}
