package com.capgemini.domain;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ReservationCancellation {
    private Date cancellationDate;
    private boolean chargeCancellationConditions;

    public ReservationCancellation(Date cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    public Date getCancellationDate() {
        return cancellationDate;
    }

    public boolean chargeCancellationConditions() {
        return chargeCancellationConditions;
    }

    public void setChargeCancellationConditions(boolean chargeCancellationConditions) {
        this.chargeCancellationConditions = chargeCancellationConditions;
    }

    public double getChargedPercentage(){
        if(!chargeCancellationConditions)
            return 0;

        Date currentTime = new Date();
        long diff = currentTime.getTime() - cancellationDate.getTime();
        int noDays = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        return getPercentage(noDays);
    }

    private double getPercentage(int noDays){
        double percentage = 0.0;

        if(noDays > 20)
            percentage = 0;
        else if(noDays <= 20 && noDays > 15)
            percentage = 25;
        else if(noDays <= 15 && noDays > 10)
            percentage = 50;
        else // if(noDays <= 10)
            percentage =  100;

        return percentage;
    }
}
