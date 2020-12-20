package com.BangMach.RestaurantUserService.model;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.sql.Timestamp;

@Entity
@Table(name="reservation_detail")
public class ReservationDetail {

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
    private Timestamp startTime;

    @Column
    private String note;

    @Column
    private String status;

    @NotEmpty
    private int tableId;

    @Column
    @NotNull
    private int seats;

    public ReservationDetail(int id, String email, String name, String phone, Date startTime, String note, String status, int tableId, int seats) {
        setId(id);
        setEmail(email);
        setName(name);
        setPhone(phone);
        setTableId(tableId);
        setSeats(seats);
        setStartTime(new Timestamp(startTime.getTime()));
        setStatus(status);
        setName(note);
    }

    public ReservationDetail() {

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

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}