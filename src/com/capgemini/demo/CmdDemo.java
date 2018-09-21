package com.capgemini.demo;

import com.capgemini.domain.Person;
import com.capgemini.domain.Receptionist;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CmdDemo extends Demo{
    private Person currentUser;
    private Scanner inputReader;

    public void run(){
        inputReader = new Scanner(System.in);

        while(currentUser == null)
            currentUser = login();

        System.out.println("Logged in as: " + currentUser.getFirstName() + " " + currentUser.getLastName());

        while(true){
            doActions();
            System.out.println("\n -------------");
        }
    }

    private Person login(){
        System.out.println("MOLVENO LAKE RESORT");
        System.out.println("Welcome. Please choose the account to login with:");

        List<Person> persons = new ArrayList<>();
        //TODO: Get users from mock repo
        Person receptionist = new Receptionist();
        receptionist.setLastName("Hendrikson");
        receptionist.setFirstName("Hendrik");
        persons.add(receptionist);

        for(int i = 0; i < persons.size(); i++){
            Person person = persons.get(i);
            String role = person instanceof Receptionist ? "receptionist" : "guest";
            System.out.println(i + ". " + person.getFirstName() + " " + person.getLastName() + " - " + role);
        }

        int index = inputReader.nextInt();
        return index <= persons.size() - 1 && index >= 0 ? persons.get(index): null;
    }

    private void doActions(){
        List<Demo> actions = new ArrayList<>();
        actions.add(new ReservationDemo(currentUser, inputReader));
        actions.add(new CheckInDemo(currentUser, inputReader));

        System.out.println("Please choose an action to perform:");
        for(int i = 0; i < actions.size(); i++){
            Demo action = actions.get(i);
            System.out.println(i + ". " + action.getDescription());
        }

        int index = inputReader.nextInt();
        Demo action = actions.get(index);

        action.run();
    }

    @Override
    public String getDescription() {
        return "Commandline demo";
    }

}
