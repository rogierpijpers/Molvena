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
        return roomService.getAllRooms();
    }

    @Secured({"ROLE_GUEST", "ROLE_ADMIN"})
    @RequestMapping("/room/{id}")
    public Room getRoomById(@PathVariable("id") short id){
        return roomService.getRoomById(id);
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value="/room/", method=RequestMethod.POST)
    public void createRoom(@RequestBody Room room) throws InvalidInputException {
        roomService.createRoom(room);
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value="/room/{id}", method=RequestMethod.PUT)
    public void updateRoom(@PathVariable("id") short id, @RequestBody Room room) throws InvalidInputException {
        roomService.updateRoom(id, room);
    }
}
