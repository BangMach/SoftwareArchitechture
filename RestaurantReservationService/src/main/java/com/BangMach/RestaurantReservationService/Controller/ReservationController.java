package com.BangMach.RestaurantReservationService.Controller;

import com.BangMach.RestaurantReservationService.Entity.Reservation;
import com.BangMach.RestaurantReservationService.Service.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/reservations/")
public class ReservationController {

    private final ReservationServiceImpl reservationService;

    @Autowired
    public ReservationController(ReservationServiceImpl reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping(value = "/")
    public Collection<Reservation> getAllReservation() {
        return reservationService.getAllReservation();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteReservationById(@PathVariable("id") int id) {
        reservationService.deleteReservationByID(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateReservation(@RequestBody Reservation  reservation) {
        reservationService.updateReservation(reservation);
    }

    @PostMapping(value = "/insert")
    public void addReservation(@RequestBody Reservation reservation) {
        reservationService.insertReservation(reservation);
    }

    @PutMapping(value = "/update")
    public void updateAccount(@RequestBody Reservation reservation) {
        reservationService.updateReservation(reservation);
    }


}
