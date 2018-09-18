package com.capgemini;

import com.capgemini.domain.RoomType;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static ArrayList<RoomType> roomTypeList;

	public static void main(String[] args) {
	}

	public static void addRoomType(RoomType type) {
		roomTypeList.add(type);
	}

	public static ArrayList<RoomType> getRoomTypeList() {
		return roomTypeList;
	}


}