package com.Restaurant.RestaurantTableService.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

/**
 * Created by CoT on 10/14/17.
 */

@Entity
@Table(name = "restaurant_table")
public class RestaurantTable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull
    private int seats;

    @Column
    @NotNull
    private String status;

    public RestaurantTable() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}
