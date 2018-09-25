package com.capgemini.web.molvenolakeresort;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from the Reservation page";
    }

    @RequestMapping("/create")
    public String create() {
        return "Create a new reservation";
    }

    @RequestMapping("/get{id}")
    public String getSingle(int id) {
        return "Get a single reservation with id: "+ id +"";
    }

    @RequestMapping("/delete{id}")
    public String delete(int id) {
        return "Delete reservation number " + id +"";
    }
}
