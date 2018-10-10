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

        roomTypeRepository.save(roomType);
        roomTypeRepository.save(roomType2);
        roomTypeRepository.save(roomType3);
        roomTypeRepository.save(roomType4);
        roomTypeRepository.save(roomType5);
        roomTypeRepository.save(roomType6);
    }

    private void seedRoomRepository(){
        Room room = new Room();
        RoomType roomType = roomTypeRepository.findAll().stream().findFirst().get();
        room.setRoomType(roomType);
        roomRepository.save(room);
    }

    private void seedReservationRepository(){
        Date startDate = new Date();
        Date endDate = addDays(startDate, 1);
        Room room = roomRepository.findAll().stream().findFirst().get();
        RoomType roomType = room.getRoomType();

        Reservation myReservation = new Reservation(startDate, endDate, (Guest) personRepository.findByMail("Jan@vandijk.nl"), 6, room, roomType);
        reservationRepository.save(myReservation);

        Reservation myReservation2 = new Reservation(startDate, endDate, (Guest) personRepository.findByMail("Tom@vandersteen.nl"), 6, room, roomType);
        reservationRepository.save(myReservation2);

        Reservation myReservation3 = new Reservation(addDays(startDate, 5), addDays(startDate, 8), (Guest) personRepository.findByMail("Jan@vandijk.nl"), 6, room, roomType);
        reservationRepository.save(myReservation3);

        Reservation myReservation4 = new Reservation(addDays(startDate, 14), addDays(startDate, 19), (Guest) personRepository.findByMail("Tom@vandersteen.nl"), 3, room, roomType);
        reservationRepository.save(myReservation4);
    }

    private void seedPersonRepository(){
        Guest guest = new Guest();
        guest.setFirstName("Tom");
        guest.setLastName("van der Steen");
        guest.setPhone("06-12345678");
        guest.setPassword("$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2");
        guest.setMail("Tom@vandersteen.nl");
        guest.setAddress("Straat 1");
        guest.setState("");
        guest.setZipCode("5555LL");
        guest.setCountry("NL");
        guest.setDateOfBirth(new Date(31-8-1994));
        guestRepository.save(guest);

        Guest guest2 = new Guest();
        guest2.setFirstName("Jan");
        guest2.setLastName("van Dijk");
        guest2.setPhone("06-87654321");
        guest2.setPassword("$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2");
        guest2.setMail("Jan@vandijk.nl");
        guest2.setAddress("Straat 1");
        guest2.setZipCode("5555LL");
        guest2.setState("");
        guest2.setCountry("NL");
        guest2.setDateOfBirth(new Date(31-8-1994));
        guestRepository.save(guest2);

        Guest guest3 = new Guest();
        guest3.setFirstName("Günther");
        guest3.setLastName("von Heẞ");
        guest3.setPhone("06-43218765");
        guest3.setPassword("$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2");
        guest3.setMail("gunther@rolldock.de");
        guest3.setAddress("Straat 1");
        guest3.setZipCode("5555LL");
        guest3.setState("");
        guest3.setCountry("NL");
        guest3.setDateOfBirth(new Date(31-8-1994));
        guestRepository.save(guest3);

        Employee employee = new Employee();
        employee.setFirstName("Henk");
        employee.setLastName("van Vliet");
        employee.setPhone("123456789");
        employee.setPassword("$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2");
        employee.setMail("Henk@vanvliet.nl");
        employee.setAddress("Straat 1");
        employee.setZipCode("5555LL");
        employee.setState("");
        employee.setCountry("NL");
        employee.setDateOfBirth(new Date(31-8-1994));
        employee.setRole("ROLE_ADMIN");
        employeeRepository.save(employee);

        Employee employee1 = new Employee();
        employee1.setFirstName("Tiny");
        employee1.setLastName("van Vliet");
        employee1.setPhone("123456789");
        employee1.setPassword("$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2");
        employee1.setMail("Receptionist@molvenolakeresort.com");
        employee1.setAddress("Straat 1");
        employee1.setZipCode("5555LL");
        employee1.setState("");
        employee1.setCountry("NL");
        employee1.setDateOfBirth(new Date(31-8-1994));
        employee1.setRole("ROLE_RECEPTIONIST");
        employeeRepository.save(employee1);
    }

    private static Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);

        return cal.getTime();
    }
}