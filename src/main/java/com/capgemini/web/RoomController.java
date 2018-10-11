package com.capgemini.web;

import com.capgemini.data.RoomTypeRepository;
import com.capgemini.domain.Room;
import com.capgemini.domain.RoomType;
import com.capgemini.service.ReservationService;
import com.capgemini.service.RoomService;
import com.capgemini.service.RoomTypeService;
import com.capgemini.web.dto.RoomTypeWithCountDTO;
import com.capgemini.web.util.exception.InvalidInputException;
import com.capgemini.web.util.exception.ObjectNotFoundException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class RoomController {

    @Autowired
    private RoomTypeRepository roomTypeRepository;


    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomTypeService roomTypeService;

    // public
    @RequestMapping("/roomtype/available/{startDate}/{endDate}")
    public List<RoomTypeWithCountDTO> getAvailableRoomTypes(@PathVariable("startDate") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) Date startDate, @PathVariable("endDate") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) Date endDate){
        return reservationService.getAllAvailableRooms(startDate, endDate).stream().collect(Collectors.groupingBy(Room::getRoomType, Collectors.counting()))
            .entrySet().stream().map(x -> new RoomTypeWithCountDTO(x.getKey(), x.getValue())).collect(Collectors.toList());
    }

    @RequestMapping("/roomtype/available/{startDate}/{endDate}/{roomTypeId}")
    public long getAvailableRoomsOfRoomType(@PathVariable("startDate") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) Date startDate, @PathVariable("endDate") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) Date endDate, @PathVariable("roomTypeId") int roomTypeId) throws ObjectNotFoundException {
        RoomType roomType = roomTypeService.getRoomTypeById(roomTypeId);
        if(roomType == null)
            throw new ObjectNotFoundException("RoomType with ID: " + roomTypeId + " not found.");

        return reservationService.getAllAvailableRooms(startDate, endDate, roomType).stream().filter(x -> x.getRoomType().equals(roomType)).count();
    }

    @RequestMapping("/roomtype/")
    public List<RoomType> getRoomTypes(){
        return roomTypeRepository.findAll();

    }

    // secured
    @Secured({"ROLE_ADMIN", "ROLE_RECEPTIONIST"})
    @RequestMapping("/room/available/{startDate}/{endDate}")
    public List<Room> getAvailableRooms(@PathVariable("startDate") Date startDate, @PathVariable("endDate") Date endDate){
        return reservationService.getAllAvailableRooms(startDate, endDate);
    }

    // secured
    @Secured({"ROLE_ADMIN", "ROLE_RECEPTIONIST"})
    @RequestMapping("/room/available/{startDate}/{endDate}/{roomTypeId}")
    public List<Room> getAvailableRoomsByRoomType(@PathVariable("startDate") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) Date startDate, @PathVariable("endDate") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) Date endDate, @PathVariable("roomTypeId") long roomTypeId){
        Optional<RoomType> roomType = roomTypeRepository.findById(roomTypeId);
        return reservationService.getAllAvailableRooms(startDate, endDate, roomType.orElse(null));
    }
    @Secured({"ROLE_ADMIN", "ROLE_RECEPTIONIST"})
    @RequestMapping("/room/")
    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
    }

    @Secured({"ROLE_ADMIN", "ROLE_RECEPTIONIST"})
    @RequestMapping("/room/{id}")
    public Room getRoomById(@PathVariable("id") short id) throws ObjectNotFoundException {
        Room room = roomService.getRoomById(id);
        if(room == null)
            throw new ObjectNotFoundException();
        else
            return room;
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

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value="/room/{id}", method=RequestMethod.DELETE)
    public void deleteRoom(@PathVariable("id") short id) throws InvalidInputException, ObjectNotFoundException {
        roomService.deleteRoom(id);
    }
}
