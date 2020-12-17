package com.BangMach.RestaurantService.model;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.stereotype.Component;

@Component
public class Food implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = -4083836730911937938L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public Food() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}