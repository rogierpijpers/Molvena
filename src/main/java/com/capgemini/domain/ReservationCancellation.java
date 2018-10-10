package com.capgemini.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity
public class ReservationCancellation {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

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
