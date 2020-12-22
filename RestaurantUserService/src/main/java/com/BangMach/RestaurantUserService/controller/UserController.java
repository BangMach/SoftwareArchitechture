package com.BangMach.RestaurantUserService.controller;

import com.BangMach.RestaurantUserService.model.Reservation;
import com.BangMach.RestaurantUserService.model.ReservationDetail;
import com.BangMach.RestaurantUserService.model.RestaurantTable;
import com.BangMach.RestaurantUserService.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping(value = "/reservation/details")
    public List<ReservationDetail> getAllReservationDetail() {
        return userService.getAllReservationDetails();
    }

    @PostMapping(value = "/create/reservation")
    public ResponseEntity createReservation(@RequestBody Reservation reservation) {
        Reservation newReservation = userService.createReservation(reservation);
        if (newReservation == null) {
            return new ResponseEntity<>(
                    "Failed to create new reservation",
                    HttpStatus.BAD_REQUEST
            );
        } else {
            return new ResponseEntity<>(
                    newReservation,
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PutMapping(value = "/update/reservation")
    public ResponseEntity updateReservation(@RequestBody Reservation reservation) {
        ResponseEntity<Reservation> updatedReservation = userService.updateReservation(reservation);
        if (updatedReservation == null) {
            return new ResponseEntity<>(
                "Failed to update reservation",
                HttpStatus.BAD_REQUEST
            );
        } else {
            return new ResponseEntity<>(
                updatedReservation.getBody(),
                HttpStatus.BAD_REQUEST
            );
        }
    }
}
