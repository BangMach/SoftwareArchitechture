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


    public Reservation(int id, String roomCode, String name, String phone, Date startTime) {
        this.id = id;
        this.roomCode = roomCode;
        this.name = name;
        this.phone = phone;
        this.startTime = startTime;
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
