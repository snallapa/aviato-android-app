package com.aviato.model;

import org.parceler.Parcel;

@Parcel
public class FlightData {
    String source;
    String destination;
    String price;
    String time;

    public FlightData() {
    }

    public FlightData(String source, String destination, String price) {
        this.source = source;
        this.destination = destination;
        this.price = price;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
