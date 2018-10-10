package com.capgemini.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class ReservationCancellationTest {

    private Date createDateMinus(int amount){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -amount);
        Date dateMinusAmount = cal.getTime();
        return dateMinusAmount;
    }

    @Test
    public void testCancellation0Percent(){
        Date date = createDateMinus(21);

        ReservationCancellation cancellation = new ReservationCancellation(date);
        cancellation.setChargeCancellationConditions(true);

        Assert.assertEquals(0, cancellation.getChargedPercentage(), 0.001);
    }

    @Test
    public void testCancellation25Percent(){
        Date date = createDateMinus(20);

        ReservationCancellation cancellation = new ReservationCancellation(date);
        cancellation.setChargeCancellationConditions(true);

        Assert.assertEquals(25, cancellation.getChargedPercentage(), 0.001);
    }

    @Test
    public void testCancellation50Percent(){
        Date date = createDateMinus(11);

        ReservationCancellation cancellation = new ReservationCancellation(date);
        cancellation.setChargeCancellationConditions(true);

        Assert.assertEquals(50, cancellation.getChargedPercentage(), 0.001);
    }

    @Test
    public void testCancellation100Percent(){
        Date date = createDateMinus(10);

        ReservationCancellation cancellation = new ReservationCancellation(date);
        cancellation.setChargeCancellationConditions(true);

        Assert.assertEquals(100, cancellation.getChargedPercentage(), 0.001);
    }

    @Test
    public void testCancellationChargeFree(){
        Date date = createDateMinus(2);

        ReservationCancellation cancellation = new ReservationCancellation(date);
        cancellation.setChargeCancellationConditions(false);

        Assert.assertEquals(0, cancellation.getChargedPercentage(), 0.001);
    }
}
