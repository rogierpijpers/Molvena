package com.capgemini.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationControllerTest {

    @Test
    public void testGetReservationsForGuest(){
        //TODO: get all reservations for a logged in guest
    }

    @Test
    public void testAddReservationAsGuest(){
        //TODO: create new reservation as guest
    }

    @Test
    public void testGetReservationsAsReceptionist(){
        //TODO: get all reservations when logged in as a receptionist
    }

    @Test
    public void testAddReservationAsReceptionist(){
        //TODO: create new reservation as receptionist for a guest
    }

    @Test
    public void testUpdateReservationAsReceptionist(){
        //TODO: update a reservation when logged in as receptionist
    }

    @Test
    public void testUpdateReservationAsGuest(){
        //TODO: update reservation as a guest !!SHOULD FAIL
    }

    @Test
    public void testDeleteReservationAsReceptionist(){
        //TODO: delete as receptionist
    }

    @Test
    public void testDeleteReceptionistAsGuest(){
        //TODO: delete as guest
    }
}
