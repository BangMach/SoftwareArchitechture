package com.BangMach.RestaurantBookingService.Entity;

import org.aspectj.lang.annotation.DeclareAnnotation;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;


@Entity
public class PresitenceReservation {
    @Id
    @NotEmpty
    private int id;

    @Email
    private String email;

    @NotEmpty
    private String name;

    @NotEmpty
    private String phone;
    @NotEmpty
    private Integer numberOfPeople;
    @NotEmpty
    private Date startTime;
    @NotEmpty
    private String roomCode;

    @javax.persistence.Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public PresitenceReservation(@NotEmpty int id, @Email String email, @NotEmpty String name, @NotEmpty String phone, @NotEmpty Integer numberOfPeople, @NotEmpty Date startTime, @NotEmpty String roomCode) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.numberOfPeople = numberOfPeople;
        this.startTime = startTime;
        this.roomCode = roomCode;
    }

    public PresitenceReservation() {
    }
}