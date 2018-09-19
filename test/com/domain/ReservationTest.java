package com.domain;

import com.capgemini.domain.Receptionist;
import com.capgemini.domain.Reservation;
import junit.framework.Assert;
import org.junit.Test;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
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

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date today = simpleDateFormat.parse("2018-09-18", new ParsePosition(0));
        Date yesterday  = simpleDateFormat.parse("2018-09-17", new ParsePosition(0));
        reservation.setEndDate(yesterday);
        // TODO: check for today


        reservation.setCheckedIn(true, new Receptionist());

        Assert.assertFalse(reservation.isCheckedIn());
    }
}
