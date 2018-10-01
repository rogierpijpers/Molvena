package com.capgemini.web;

import com.capgemini.data.RoomRepository;
import com.capgemini.domain.Reservation;
import com.capgemini.domain.Room;
import com.capgemini.domain.RoomType;
import com.capgemini.service.ReservationService;
import com.capgemini.service.RoomService;
import com.capgemini.web.util.exception.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RoomController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomRepository roomRepository;

    // public
    @RequestMapping("/roomtype/available/{startDate}/{endDate}")
    public List<RoomType> getAvailableRoomTypes(@PathVariable("startDate") Date startDate, @PathVariable("endDate") Date endDate){
        return reservationService.getAllAvailableRooms(startDate, endDate).stream().map(x -> x.getRoomType()).collect(Collectors.toList());
    }

    // secured

    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/room/available/{startDate}/{endDate}")
    public List<Room> getAvalailableRooms(@PathVariable("startDate") Date startDate, @PathVariable("endDate") Date endDate){
        return reservationService.getAllAvailableRooms(startDate, endDate);
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/room/")
    public List<Room> getAllRooms(){
        return roomRepository.getAllRooms();
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value="/reservation/", method=RequestMethod.POST)
    public void createRoom(@RequestBody Room room) throws InvalidInputException {
        roomService.createRoom(room);
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value="/reservarion/{id}", method=RequestMethod.PUT)
    public void updateRoom(@PathVariable("id") int id, @RequestBody Room room){
        roomService.updateRoom(id, room);
    }
}
