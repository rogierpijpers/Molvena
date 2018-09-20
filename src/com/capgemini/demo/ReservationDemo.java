package com.capgemini.demo;

import com.capgemini.domain.*;
import com.capgemini.service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ReservationDemo extends Demo {
    private Person currentUser;
    private Scanner inputReader;

    public ReservationDemo(Person currentUser, Scanner inputReader){
        this.currentUser = currentUser;
        this.inputReader = inputReader;
    }

    @Override
    public void run() {
        initTestData();

        System.out.println("-- Running reservation demo");

        System.out.println("Please enter the number of persons");
        int noPersons = inputReader.nextInt();

        System.out.println("Please enter the start date with format dd-MM-yyyy");
        Date startDate = readDate();

        System.out.println("Please enter the end date with format dd-MM-yyyy");
        Date endDate = readDate();

        System.out.println("Please select a room");
        List<Room> availableRooms = reservationService.getAllAvailableRooms(startDate, endDate);
        for(int i = 0; i < availableRooms.size(); i++)
            System.out.println(i + ". " + availableRooms.get(i));

        int roomIndex = inputReader.nextInt();
        Room selectedRoom = availableRooms.get(roomIndex);

        System.out.println("Please press [y] to confirm reservation or [n] to cancel");
        if(inputReader.nextLine().toLowerCase().equals("n"))
            return;

        if(currentUser instanceof Receptionist)
            makeReservationAsReceptionist(noPersons, startDate, endDate, selectedRoom);
        else
            makeReservationAsGuest(noPersons, startDate, endDate, selectedRoom);
    }

    private void makeReservationAsReceptionist(int noPersons, Date startDate, Date endDate, Room room){
        System.out.println("Please select a guest");
        int i = 0;
        for(Guest guest : guestRepository.getAllGuests()){
            System.out.println(i + ". " + guest.getFirstName() + " " + guest.getLastName());
            i++;
        }

        int guestId = inputReader.nextInt();
        Guest guest = guestRepository.getAllGuests().get(guestId);
        Reservation reservation = new Reservation(startDate, endDate, guest, noPersons, room, room.getRoomType());
        reservationService.addReservation(reservation);
        System.out.println("Reservation successfull");
        System.out.println(reservation);
    }

    private void makeReservationAsGuest(int noPersons, Date startDate, Date endDate, Room room){
        Reservation reservation = new Reservation(startDate, endDate, (Guest) currentUser, noPersons, room, room.getRoomType());
        reservationService.addReservation(reservation);
        System.out.println("Reservation successfull");
        System.out.println(reservation);
    }

    private Date readDate(){
        Date date = null;
        while(date == null)
            try{
                date = getDate();
            }catch (ParseException e){
                System.out.println("Input was not a valid date");
            }

        return date;
    }

    private Date getDate() throws ParseException {
        String dateStr = inputReader.nextLine();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date;
        return format.parse(dateStr);
    }

    @Override
    public String getDescription() {
        return "Make a reservation";
    }
}
