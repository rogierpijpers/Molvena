package com.capgemini.web.dto;

public class CancelReservationDTO {
    private int reservationId;
    private boolean chargeCancellationConditions = true;

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public boolean chargeCancellationConditions() {
        return chargeCancellationConditions;
    }

    public void setChargeCancellationConditions(boolean chargeCancellationConditions) {
        this.chargeCancellationConditions = chargeCancellationConditions;
    }
}
