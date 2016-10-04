package com.aviato.model;

/**
 * Created by sahith on 10/1/16.
 */

public class ChooseData {
    String fId;
    int age;
    String name;
    String profileUrl;
    boolean chosen;

    public ChooseData() {
    }

    public ChooseData(int age, String name, String profileUrl) {
        this.age = age;
        this.name = name;
        this.profileUrl = profileUrl;
//        this.profileUrl = "https://scontent.xx.fbcdn.net/v/t1.0-1/p50x50/1538635_10152313443182867_788243216_n.jpg?oh=d69893cfe829b4706a875a37585e4a60&oe=5877FC0C";
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public boolean isChosen() {
        return chosen;
    }

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }
}
