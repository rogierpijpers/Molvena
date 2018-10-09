package com.capgemini.domain;

import com.capgemini.TestJpaConfig;
import com.capgemini.data.GuestRepository;
import com.capgemini.data.ReservationRepository;
import com.capgemini.data.RoomRepository;
import com.capgemini.data.RoomTypeRepository;
import com.capgemini.domain.Guest;
import com.capgemini.domain.Reservation;
import com.capgemini.domain.Room;
import com.capgemini.domain.RoomType;
import com.capgemini.service.ReservationService;
import com.capgemini.service.RoomTypeService;
import com.capgemini.web.MolvenolakeresortApplication;
import com.capgemini.web.util.DummyDataSeeder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MolvenolakeresortApplication.class, TestJpaConfig.class }, loader = AnnotationConfigContextLoader.class)
@Transactional
public class ReservationTests {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ReservationService reservationService;
    @Autowired
    RoomTypeRepository roomTypeRepository;
    @Autowired
    GuestRepository guestRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RoomTypeService roomTypeService;

    Guest guest;
    Date startDate;
    Date endDate;
    Room room;
    RoomType roomType;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @Before
    public void initReservationData() {
        MockitoAnnotations.initMocks(this);

        guest = new Guest();
        guest.setLastName("van de Moosdijk");
        guest.setFirstName("Thom");
        guest.setPassword("dummypassword");
        guest.setCity("Utrecht");
        guest.setMail("Thom@moosjes.nl");
        guest.setAddress("Reykjavikplein 1");
        guestRepository.save(guest);

        try {
            startDate = dateFormat.parse("19-04-2018");
            endDate = dateFormat.parse("19-05-2018");
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        room = new Room();
        roomTypeRepository.save(new RoomType((byte) 2, (byte) 0));
        roomTypeRepository.save(new RoomType((byte) 4, (byte) 0));
        roomTypeRepository.save(new RoomType((byte) 6, (byte) 0));
        roomTypeRepository.save(new RoomType((byte) 2, (byte) 1));
        roomTypeRepository.save(new RoomType((byte) 2, (byte) 2));
        roomTypeRepository.save(new RoomType((byte) 2, (byte) 3));
        List<RoomType> types = roomTypeRepository.findAll();
        roomType = roomTypeRepository.findAll().stream().findFirst().get();
        room.setRoomType(roomType);
        roomRepository.save(room);
    }

    @Test
    public void testReservationConstructor() {
        Reservation myReservation = new Reservation(startDate, endDate, guest, 6, room, roomType);
        long reservationId = reservationRepository.save(myReservation).getReservationID();

        Reservation reservation = reservationService.getReservationByID(reservationId);

        // Test if the submitted parameters doesn't get corrupted along the way.
        assertEquals(myReservation, reservation);
    }

    @Test
    public void testGetReservationByID() {
        Reservation myReservation = new Reservation(startDate, endDate, guest, 6, room, roomType);
        reservationRepository.save(myReservation);

        Reservation reservation = reservationService.getReservationByID(myReservation.getReservationID());
        // if(myReservation.equals(reservation))
        // Tests if the correct reservation returns correctly by GetReservationID();
        assertEquals(myReservation.getReservationID(), reservation.getReservationID());
    }

    @Test
    public void testGetReservationByName() {
        Reservation myReservation = new Reservation(startDate, endDate, guest, 6, room, roomType);
        reservationRepository.save(myReservation);

        reservationRepository.save(myReservation);

        Reservation reservation = reservationService.getReservationByName("van de Moosdijk");
        // Tests if the right reservation returns when using GetReservationByName();
        assertEquals(myReservation.getGuest().getLastName(), reservation.getGuest().getLastName());
    }

    @Test
    public void testGetAllReservations() {
        Reservation myReservation1 = new Reservation(startDate, endDate, guest, 6, room, roomType);
        Reservation myReservation2 = new Reservation(startDate, endDate, guest, 6, room, roomType);

        reservationRepository.save(myReservation1);
        reservationRepository.save(myReservation2);

        ArrayList<Reservation> reservationArrayList = new ArrayList<>();
        reservationArrayList.add(myReservation1);
        reservationArrayList.add(myReservation2);

        // Test if the array object itself is equal.
        assertEquals(reservationArrayList, reservationRepository.findAll());
        // Test if the size of the arrays are equal (is this necessary?)
        assertEquals((reservationArrayList.size()), reservationRepository.findAll().size());
    }
}