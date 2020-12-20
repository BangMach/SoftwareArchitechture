package com.BangMach.RestaurantUserService.controller;

import com.BangMach.RestaurantUserService.model.Reservation;
import com.BangMach.RestaurantUserService.model.ReservationDetail;
import com.BangMach.RestaurantUserService.model.RestaurantTable;
import com.BangMach.RestaurantUserService.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(path = "/users/")
public class UserController {

    private final UserServiceInterface userService;

    @Autowired
    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/available/tables")
    public List<RestaurantTable> searchAvailableTables(@RequestParam Timestamp startTime) {
        return userService.searchAvailableTables(startTime);
    }

    @GetMapping(value = "/reservation/detail")
    public List<ReservationDetail> getAllReservationDetail() {
        return userService.getAll();
    }

    @PostMapping(value = "/reservation/create")
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return userService.createReservation(reservation);
    }

    @PutMapping(value = "/reservation/update")
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation) {
        return userService.updateReservation(reservation);
    }
}
