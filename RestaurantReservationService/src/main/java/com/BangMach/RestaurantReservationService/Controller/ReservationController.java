package com.BangMach.RestaurantReservationService.Controller;

import com.BangMach.RestaurantReservationService.Entity.Reservation;
import com.BangMach.RestaurantReservationService.Service.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "/all")
    public Collection<Reservation> getAllReservation() {
        return reservationService.getAllReservation();
    }

    @DeleteMapping(value = "/delete")
    public String deleteReservationById(@RequestParam int id) {
        return reservationService.deleteReservationByID(id);
    }

    @GetMapping(value = "/find")
    public Reservation findById(@RequestParam int id) {
        return reservationService.findReservationById(id);
    }

    @PostMapping(value = "/create")
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    @PutMapping(value = "/update")
    public Reservation updateAccount(@RequestBody Reservation reservation) {
        return reservationService.updateReservation(reservation);
    }

}
