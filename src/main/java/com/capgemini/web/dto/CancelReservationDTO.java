package com.capgemini.web.dto;

public class CancelReservationDTO {
    private long reservationId;
    private boolean chargeCancellationConditions;

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public boolean chargeCancellationConditions() {
        return chargeCancellationConditions;
    }

    public void setChargeCancellationConditions(boolean chargeCancellationConditions) {
        this.chargeCancellationConditions = chargeCancellationConditions;
    }
}
