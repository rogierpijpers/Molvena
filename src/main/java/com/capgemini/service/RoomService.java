package com.capgemini.service;

import com.capgemini.data.RoomRepository;
import com.capgemini.domain.Room;
import com.capgemini.web.util.exception.InvalidInputException;
import com.capgemini.web.util.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public void createRoom(Room room) throws InvalidInputException {
        if(validate(room))
            roomRepository.save(room);
        else
            throw new InvalidInputException();
    }

    private boolean validate(Room room){
        return room.getRoomType() != null && room.getRoomID() >= 0;
    }

    public void updateRoom(short id, Room room) throws InvalidInputException {
        if(validate(room))
            roomRepository.save(room);
        else
            throw new InvalidInputException();
    }

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    public Room getRoomById(long roomId) throws ObjectNotFoundException {
        Optional<Room> opt = roomRepository.findById(roomId);
        if(opt.isPresent())
            return opt.get();
        else
            throw new ObjectNotFoundException();
    }

    public void deleteRoom(long roomId) throws InvalidInputException, ObjectNotFoundException {
        Room room = getRoomById(roomId);
        if(room == null)
            throw new InvalidInputException();

        roomRepository.delete(room);
    }
}
