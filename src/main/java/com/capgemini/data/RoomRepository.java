package com.capgemini.data;

import com.capgemini.domain.Room;
import com.capgemini.domain.RoomType;

import java.util.ArrayList;
import java.util.List;

public class RoomRepository {

    private List<Room> rooms;

    public RoomRepository() {

        rooms = new ArrayList();
    }

    public List<Room> getAllRooms() {
        return rooms;
    }

    public void addRoom(Room room){

        rooms.add(room);
    }

    //Get a single room by roomnumber
    public Room getRoomByRoomNumber(short roomID) {
        List<Room> allRooms = getAllRooms();
        for (Room room : allRooms) {
            if (room.getRoomID() == roomID)
                return room;
        }
        return null;
    }
}
