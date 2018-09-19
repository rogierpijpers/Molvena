package com.domain;

import com.capgemini.domain.Reservation;
import junit.framework.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ReservationTest {

    @Test
    public void ReceptionistChecksGuestInBeforeEndDate() {
        Reservation reservation = new Reservation();

        String pattern = "yyyy-MM-dd";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date reservationEndDate = simpleDateFormat.parse("22-02-2017");
            Date checkInDate = simpleDateFormat.parse("21-0202017");

            reservation.setEndDate(reservationEndDate);

            reservation.setCheckedIn(true, checkInDate);
            Assert.assertTrue(reservation.isCheckedIn());

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    public void ReceptionistChecksGuestInAfterEndDate() {
        Reservation reservation = new Reservation();

        String pattern = "yyyy-MM-dd";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date reservationEndDate = simpleDateFormat.parse("22-02-2017");
            Date checkInDate = simpleDateFormat.parse("23-0202017");

            reservation.setEndDate(reservationEndDate);

            reservation.setCheckedIn(true, checkInDate);
            Assert.assertFalse(reservation.isCheckedIn());

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    public void OnReservationConstructIsCheckedInIsFalse() {
        Reservation reservation = new Reservation();


        Assert.assertSame(false, reservation.isCheckedIn());
    }
}
