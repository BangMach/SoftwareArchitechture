package com.BangMach.RestaurantReservationService.Controller;

import com.BangMach.RestaurantReservationService.Entity.Reservation;
import com.BangMach.RestaurantReservationService.Service.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationServiceImpl reservationService;

    @Autowired
    public ReservationController(ReservationServiceImpl reservationService) {
        this.reservationService = reservationService;
    }

    @DeleteMapping(value = "/{id}")
    public String deleteReservationById(@PathVariable int id) {
        return reservationService.deleteReservationByID(id);
    }

    @GetMapping(value = "/{id}")
    public Reservation findById(@PathVariable int id) {
        return reservationService.findReservationById(id);
    }

    @PostMapping(value = "")
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    @PutMapping(value = "")
    public Reservation updateAccount(@RequestBody Reservation reservation) {
        return reservationService.updateReservation(reservation);
    }

}
