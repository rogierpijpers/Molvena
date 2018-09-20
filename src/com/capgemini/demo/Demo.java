package com.capgemini.demo;

import com.capgemini.data.GuestRepository;
import com.capgemini.data.ReservationRepository;
import com.capgemini.data.RoomRepository;
import com.capgemini.data.RoomTypeRepository;
import com.capgemini.domain.Guest;
import com.capgemini.domain.Room;
import com.capgemini.domain.RoomType;
import com.capgemini.service.ReservationService;

public abstract class Demo {
    protected ReservationService reservationService;
    protected GuestRepository guestRepository;

    public abstract void run();
    public abstract String getDescription();

    protected void initTestData(){
        RoomRepository roomRepository = createRoomRepository();
        ReservationRepository reservationRepository = createReservationRepository();

        reservationService = new ReservationService();
        reservationService.setReservationRepository(reservationRepository);
        reservationService.setRoomRepository(roomRepository);

        guestRepository = createGuestRepository();
    }

    private GuestRepository createGuestRepository(){
        GuestRepository guestRepository = new GuestRepository();
        Guest guest = new Guest();
        guest.setFirstName("Henk");
        guest.setLastName("de Vries");
        guest.setAddress("Reykjavikplein 1");
        guest.setCity("Utrecht");
        guest.setMail("henk.devries@dummyemail.com");
        guest.setPhone("555-445566");
        guestRepository.addGuest(guest);

        return guestRepository;
    }

    private RoomRepository createRoomRepository(){
        RoomRepository roomRepository = new RoomRepository();
        RoomType roomType1 = new RoomType((byte) 0, (byte) 2);
        Room room1 = new Room();
        room1.setRoomType(roomType1);

        roomRepository.addRoom(room1);
        return roomRepository;
    }

    private ReservationRepository createReservationRepository(){
        return new ReservationRepository();
    }

    private RoomTypeRepository createRoomTypeRepository(){
        RoomTypeRepository roomTypeRepository = new RoomTypeRepository();
        roomTypeRepository.addRoomType(new RoomType((byte) 2, (byte) 0));
        roomTypeRepository.addRoomType(new RoomType((byte) 4, (byte) 0));
        roomTypeRepository.addRoomType(new RoomType((byte) 6, (byte) 0));
        roomTypeRepository.addRoomType(new RoomType((byte) 2, (byte) 1));
        roomTypeRepository.addRoomType(new RoomType((byte) 2, (byte) 2));
        roomTypeRepository.addRoomType(new RoomType((byte) 2, (byte) 3));
        return roomTypeRepository;
    }
}
