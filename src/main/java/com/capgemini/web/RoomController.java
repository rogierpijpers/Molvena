package com.capgemini.web;

import com.capgemini.data.RoomRepository;
import com.capgemini.domain.Room;
import com.capgemini.domain.RoomType;
import com.capgemini.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RoomController {

    @Autowired
    private ReservationService reservationService;

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

    //TODO: for owner
        // - add room
        // - update room
}
