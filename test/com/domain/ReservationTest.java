package com.domain;

import com.capgemini.domain.Receptionist;
import com.capgemini.domain.Reservation;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Date;


public class ReservationTest {

    @Test
    public void OnReservationConstructIsCheckedInIsFalse(){
        Reservation reservation = new Reservation();

        Assert.assertSame(false, reservation.isCheckedIn());
    }

    @Test
    public void checkInAsReceptionistBeforeEndDate(){
        Reservation reservation = new Reservation();
        long currentDateInInt = new Date().getTime();
        long tomorrowInInt = currentDateInInt + (3600000*24);
        Date tomorrow = new Date(tomorrowInInt);

        reservation.setEndDate(tomorrow);
        reservation.setCheckedIn(true,new Receptionist());

        Assert.assertTrue(reservation.isCheckedIn());
    }

    @Test
    public void CheckInAsReceptionistAfterEndDateTest(){
        Reservation reservation = new Reservation();
        long currentDateInInt = new Date().getTime();
        long yesterdayInInt = currentDateInInt - (3600000*24);
        Date yesterday = new Date(yesterdayInInt);

        reservation.setEndDate(yesterday);
        reservation.setCheckedIn(true,new Receptionist());

        Assert.assertFalse(reservation.isCheckedIn());
    }
}
