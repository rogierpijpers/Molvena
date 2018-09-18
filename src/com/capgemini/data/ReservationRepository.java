package com.capgemini.data;

import com.capgemini.domain.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationRepository {

    public List<Reservation> getAllReservations() {
        List<Reservation> reservationList = new ArrayList();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        for (int i = 0; i < 5; i++) {
            Reservation reservation = new Reservation();
            try {
                Date startdate = dateFormat.parse("19-04-2018");
                Date enddate = dateFormat.parse("25-04-2018");
                reservation.setStartDate(startdate);
                reservation.setEndDate(enddate);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            reservationList.add(new Reservation());
        }
        return reservationList;
    }
}
