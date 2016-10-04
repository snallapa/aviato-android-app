package com.aviato.rest;

import com.aviato.model.FlightData;

/**
 * Created by sahith on 10/2/16.
 */

public class BookingRequest {

    String Ap;
    String Dp;
    FlightData flightData;

    public BookingRequest() {
    }

    public BookingRequest(String ap, String dp, FlightData flightData) {
        Ap = ap;
        Dp = dp;
        this.flightData = flightData;
    }

    public String getAp() {
        return Ap;
    }

    public void setAp(String ap) {
        Ap = ap;
    }

    public String getDp() {
        return Dp;
    }

    public void setDp(String dp) {
        Dp = dp;
    }

    public FlightData getFlightData() {
        return flightData;
    }

    public void setFlightData(FlightData flightData) {
        this.flightData = flightData;
    }
}
