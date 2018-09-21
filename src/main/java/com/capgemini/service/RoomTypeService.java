package com.capgemini.service;

public class RoomTypeService {

    public byte getAmountOfPeople(byte singleBeds, byte doubleBeds) {
        byte people = (byte) (singleBeds + (doubleBeds * 2));
        return people;
    }
}
