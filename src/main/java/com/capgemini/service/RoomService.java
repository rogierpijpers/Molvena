package com.capgemini.service;

import com.capgemini.data.RoomRepository;
import com.capgemini.domain.Room;
import com.capgemini.web.util.exception.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;

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
        return room.getRoomType() != null && room.getRoomID() > 0;
    }

    public void updateRoom(int id, Room room) throws InvalidInputException {
        if(validate(room))
            roomRepository.updateRoom(id, room);
        else
            throw new InvalidInputException();
    }
}
