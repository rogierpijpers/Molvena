package com.capgemini.service;

import com.capgemini.data.RoomRepository;
import com.capgemini.domain.Room;
import com.capgemini.web.util.exception.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public void createRoom(Room room) throws InvalidInputException {
        if(validate(room))
            roomRepository.addRoom(room);
        else
            throw new InvalidInputException();
    }

    private boolean validate(Room room){
        return room.getRoomType() != null && room.getRoomID() >= 0;
    }

    public void updateRoom(short id, Room room) throws InvalidInputException {
        if(validate(room))
            roomRepository.updateRoom(id, room);
        else
            throw new InvalidInputException();
    }

    public List<Room> getAllRooms(){
        return roomRepository.getAllRooms();
    }

    public Room getRoomById(short roomId){
        return roomRepository.getRoomByRoomNumber(roomId);
    }

    public void deleteRoom(short roomId) throws InvalidInputException {
        Room room = roomRepository.getRoomByRoomNumber(roomId);
        if(room == null)
            throw new InvalidInputException();

        roomRepository.deleteRoom(room);
    }
}
