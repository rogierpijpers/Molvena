package com.capgemini.demo;

import com.capgemini.domain.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        System.out.println("-- Running reservation demo");

        System.out.println("Please enter the start date with format dd-MM-yyyy");
        Date startDate = readDate();

        System.out.println("Please enter the end date with format dd-MM-yyyy");
        Date endDate = readDate();

        System.out.println("Please enter the number of persons");
        int noPersons = inputReader.nextInt();

        // print room types available

        // make reservation or cancel

        // get info
        // - if receptionist -> pick guest or create new
        // - if guest -> use current info
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
