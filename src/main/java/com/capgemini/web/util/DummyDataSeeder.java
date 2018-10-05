package com.capgemini.web.util;

import com.capgemini.data.*;
import com.capgemini.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class DummyDataSeeder {
    private Logger logger = LoggerFactory.getLogger(DummyDataSeeder.class);

    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomTypeRepository roomTypeRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostConstruct
    public void seedTestData(){
        logger.info("Seeding repositories with dummy data.");
        
        seedPersonRepository();
        seedRoomTypeRepository();
        seedRoomRepository();
        seedReservationRepository();
    }

    private void seedRoomTypeRepository(){
        RoomType roomType = new RoomType((byte) 1, (byte) 1);
        RoomType roomType2 = new RoomType((byte) 2, (byte) 0);
        RoomType roomType3 = new RoomType((byte) 3, (byte) 0);
        RoomType roomType4 = new RoomType((byte) 4, (byte) 0);
        RoomType roomType5 = new RoomType((byte) 0, (byte) 1);
        RoomType roomType6 = new RoomType((byte) 0, (byte) 2);
//
        roomTypeRepository.addRoomType(roomType);
        roomTypeRepository.addRoomType(roomType2);
        roomTypeRepository.addRoomType(roomType3);
        roomTypeRepository.addRoomType(roomType4);
        roomTypeRepository.addRoomType(roomType5);
        roomTypeRepository.addRoomType(roomType6);
    }

    private void seedRoomRepository(){
        Room room = new Room();
        RoomType roomType = roomTypeRepository.getAllRoomTypes().stream().findFirst().get();
        room.setRoomType(roomType);
        roomRepository.addRoom(room);
    }

    private void seedReservationRepository(){
//        Date startDate = new Date();
//        Date endDate = addDays(startDate, 1);
//        RoomType roomType = roomTypeRepository.getAllRoomTypes().stream().findFirst().get();
//        Room room = roomRepository.getAllRooms().stream().findFirst().get();
//
//        Reservation myReservation = new Reservation(startDate, endDate, (Guest) personRepository.getSinglePerson("Jan@vandijk.nl"), 6, room, roomType);
//        reservationRepository.addReservation(myReservation);

//        Date startDate = new Date();
//        Date endDate = addDays(startDate, 1);
//        RoomType roomType = roomTypeRepository.getAllRoomTypes().stream().findFirst().get();
//        Room room = roomRepository.getAllRooms().stream().findFirst().get();
//
//        for(int i = 0; i < 5; i++){
//            Reservation myReservation = new Reservation(startDate, endDate, (Guest) personRepository.getSinglePerson("Jan@vandijk.nl"), 6, room, roomType);
//            reservationRepository.addReservation(myReservation);
//        }
    }

    private void seedPersonRepository(){
//        Guest guest = new Guest();
//        guest.setFirstName("Thom");
//        guest.setLastName("vd Moosdijk");
//        guest.setPhone("123456789");
//        guest.setPassword("$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2");
//        guest.setMail("Thom@moosjes.nl");
//        guest.setAddress("Straat 1");
//        guest.setState("");
//        guest.setZipCode("5555LL");
//        guest.setCountry("NL");
//        guest.setDateOfBirth(new Date(31-8-1994));
//        guestRepository.addGuest(guest);
//
//        Guest guest2 = new Guest();
//        guest2.setFirstName("Jan");
//        guest2.setLastName("van Dijk");
//        guest2.setPhone("123456789");
//        guest2.setPassword("$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2");
//        guest2.setMail("Jan@vandijk.nl");
//        guest2.setAddress("Straat 1");
//        guest2.setZipCode("5555LL");
//        guest2.setState("");
//        guest2.setCountry("NL");
//        guest2.setDateOfBirth(new Date(31-8-1994));
//        guestRepository.addGuest(guest2);

//        Employee employee = new Employee();
//        employee.setFirstName("Henk");
//        employee.setLastName("van Vliet");
//        employee.setPhone("123456789");
//        employee.setPassword("$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2");
//        employee.setMail("Henk@vanvliet.nl");
//        employee.setAddress("Straat 1");
//        employee.setZipCode("5555LL");
//        employee.setState("");
//        employee.setCountry("NL");
//        employee.setDateOfBirth(new Date(31-8-1994));
//        employee.setRole("ROLE_ADMIN");
//        employeeRepository.addEmployee(employee);
//
//        Employee employee2 = new Employee();
//        employee2.setFirstName("Corry");
//        employee2.setLastName("van Vliet");
//        employee2.setPhone("123456789");
//        employee2.setPassword("$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2");
//        employee2.setMail("Corry@vanvliet.nl");
//        employee2.setAddress("Straat 1");
//        employee2.setZipCode("5555LL");
//        employee2.setState("");
//        employee2.setCountry("NL");
//        employee2.setDateOfBirth(new Date(31-8-1994));
//        employee2.setRole("ROLE_RECEPTIONIST");
//        employeeRepository.addEmployee(employee2);

        Employee employee = new Employee();
        employee.setFirstName("Henk");
        employee.setLastName("van Vliet");
        employee.setPhone("123456789");
        employee.setPassword("$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2");
        employee.setMail("Receptionist@molveno.com");
        employee.setAddress("Straat 1");
        employee.setZipCode("5555LL");
        employee.setState("");
        employee.setCountry("NL");
        employee.setDateOfBirth(new Date(31-8-1994));
        employee.setRole("ROLE_ADMIN");
        employeeRepository.addEmployee(employee);
    }

    private static Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);

        return cal.getTime();
    }
}
