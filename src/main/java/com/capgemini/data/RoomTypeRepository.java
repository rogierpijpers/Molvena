package com.capgemini.data;

import com.capgemini.domain.RoomType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomTypeRepository {

    private ArrayList<RoomType> roomTypeList = new ArrayList<>();

    public void addRoomType(RoomType type) {
        roomTypeList.add(type);
    }

    public RoomType getRoomType(int n) {
        return roomTypeList.get(n);
    }

    public List<RoomType> getAllRoomTypes(){
        return roomTypeList;
    }

}
