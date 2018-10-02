package com.capgemini.service;

import com.capgemini.domain.RoomType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeService {

    public byte getAmountOfPeople(byte singleBeds, byte doubleBeds) {
        byte people = (byte) (singleBeds + (doubleBeds * 2));
        return people;
    }
}
