package com.capgemini.service;

import com.capgemini.domain.RoomType;

import java.util.List;

public class RoomTypeService {

    public byte getAmountOfPeople(byte singleBeds, byte doubleBeds) {
        byte people = (byte) (singleBeds + (doubleBeds * 2));
        return people;
    }
}
