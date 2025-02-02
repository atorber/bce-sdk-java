package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * RdsReservation
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsReservation {

    private Integer reservationLength;
    private String reservationTimeUnit;

    public Integer getReservationLength() {
        return reservationLength;
    }

    public void setReservationLength(Integer reservationLength) {
        this.reservationLength = reservationLength;
    }

    public String getReservationTimeUnit() {
        return reservationTimeUnit;
    }

    public void setReservationTimeUnit(String reservationTimeUnit) {
        this.reservationTimeUnit = reservationTimeUnit;
    }
}
