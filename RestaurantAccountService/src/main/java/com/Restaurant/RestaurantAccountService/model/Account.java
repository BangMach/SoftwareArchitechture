package com.Restaurant.RestaurantAccountService.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

/**
 * Created by CoT on 10/14/17.
 */

@Entity
@Table(name = "account")
public class Account {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String fullName;

    @Column
    @NotNull
    private String username;

    @Column
    @NotNull
    private String password;

    @Column
    private String phone;

    @Column
    private String address;

    @Column
    @NotNull
    private String email;

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
