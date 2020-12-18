package com.BangMach.RestaurantBookingService.Entity;

import java.util.Date;

public class Reservation {
    private int id;
    private String name;
    private String phone;
    private String email;
    private Integer numberOfPeople;
    private Date startTime;
    private String roomCode;

    public Reservation(int id, String name, String phone, String email, Integer numberOfPeople, Date startTime, String roomCode) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.numberOfPeople = numberOfPeople;
        this.startTime = startTime;
        this.roomCode = roomCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
