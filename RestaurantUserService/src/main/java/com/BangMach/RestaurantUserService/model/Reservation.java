package com.BangMach.RestaurantUserService.model;

import com.BangMach.RestaurantUserService.engine.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Entity
@Table(name="reservation")
public class Reservation {

    @Id
    @NotEmpty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Email
    private String email;

    @NotEmpty
    private String name;

    @NotEmpty
    private String phone;

    @NotEmpty
    @JsonSerialize(using = CustomDateSerializer.class)
    private Timestamp startTime;

    @Column
    private String note;

    @Column
    private String status;

    @NotEmpty
    private int tableId;

    public Reservation() {
    }

    public Reservation(@NotEmpty int id, @Email String email, @NotEmpty String name, @NotEmpty String phone, @NotEmpty Timestamp startTime, String note, String status, @NotEmpty int tableId) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.startTime = startTime;
        this.note = note;
        this.status = status;
        this.tableId = tableId;
    }

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

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}