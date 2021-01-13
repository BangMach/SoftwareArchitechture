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

    @GetMapping(value = "/tables/{startTime}")
    public List<RestaurantTable> searchAvailableTables(@PathVariable Timestamp startTime) {
        return userService.searchAvailableTables(startTime);
    }

    @GetMapping(value = "/reservations")
    public List<ReservationDetail> getAllReservationDetails(@RequestParam(value= "startAt", defaultValue = "0") Integer startAt, @RequestParam(value= "maxResults", defaultValue = "50") Integer maxResults) {
        return userService.getAllReservationDetails(startAt, maxResults);
    }

    @PostMapping(value = "/reservations/attributes")
    public List<ReservationDetail> findReservationDetails(@RequestBody ReservationDetail reservationDetail, @RequestParam(value= "startAt", defaultValue = "0") Integer startAt, @RequestParam(value= "maxResults", defaultValue = "50") Integer maxResults) {
        return userService.findReservationDetails(reservationDetail, startAt, maxResults);
    }

    @PostMapping(value = "/reservations")
    public ResponseEntity createReservation(@RequestBody Reservation reservation) {
        String newReservation = userService.createReservation(reservation);
        if (newReservation == null) {
            return new ResponseEntity<>(
                    "Failed to create new reservation",
                    HttpStatus.BAD_REQUEST
            );
        } else {
            return new ResponseEntity<>(
                    newReservation,
                    HttpStatus.OK
            );
        }
    }

    @PutMapping(value = "/reservations")
    public ResponseEntity updateReservation(@RequestBody Reservation reservation) {
        String updatedReservation = userService.updateReservation(reservation);
        if (updatedReservation == null) {
            return new ResponseEntity<>(
                    "Failed to update reservation",
                    HttpStatus.BAD_REQUEST
            );
        } else {
            return new ResponseEntity<>(
                    updatedReservation,
                    HttpStatus.OK
            );
        }
    }


}