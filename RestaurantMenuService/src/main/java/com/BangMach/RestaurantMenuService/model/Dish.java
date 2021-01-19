package com.BangMach.RestaurantMenuService.model;

import org.springframework.stereotype.Component;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Component
@Entity
@Table(name = "dish")
public class Dish implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = -4083836730911937938L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotEmpty
    private String name;

    @Column
    @NotEmpty
    private String category;

    @Column
    private String description;

    @Column
    private String imagePath;

    public Dish() {
    }

    public Dish(int id, String name, String category, String description, String imagePath) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.imagePath = imagePath;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}