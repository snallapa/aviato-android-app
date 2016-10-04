package com.aviato.model;

import org.parceler.Parcel;

import java.util.Date;
@Parcel
public class NotificationData {
    String userName;
    boolean success;
    boolean pending;
    String source;
    String destination;

    public String getProfileURl() {
        return profileURl;
    }

    public void setProfileURl(String profileURl) {
        this.profileURl = profileURl;
    }

    String profileURl;

    public NotificationData() {
    }

    public NotificationData(String userName, boolean success, boolean pending, String source, String destination) {
        this.userName = userName;
        this.success = success;
        this.pending = pending;
        this.source = source;
        this.destination = destination;
        this.profileURl = "";
    }

    public NotificationData(String userName, boolean success, boolean pending, String source, String destination, String profileURl) {
        this.userName = userName;
        this.success = success;
        this.pending = pending;
        this.source = source;
        this.destination = destination;
        this.profileURl = profileURl;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
