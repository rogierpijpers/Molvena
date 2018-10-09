package com.capgemini.service;

import com.capgemini.data.RoomTypeRepository;
import com.capgemini.domain.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeService {
    @Autowired
    private RoomTypeRepository roomTypeRepository;

    public byte getAmountOfPeople(byte singleBeds, byte doubleBeds) {
        byte people = (byte) (singleBeds + (doubleBeds * 2));
        return people;
    }

    public RoomType getRoomTypeById(int id){
        return roomTypeRepository.getRoomType(id);
    }
}
