package com.BangMach.RestaurantBookingService.Controller;


import com.BangMach.RestaurantBookingService.Entity.Reservation;
import com.BangMach.RestaurantBookingService.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    public ReservationController() {
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public List<Reservation> getAllReservation() {
        List<Reservation> hotels = this.reservationService.getAllReservation();
        return reservationService.getAllReservation();
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
    public Reservation getReservationById(@PathVariable("id") int id) {
        return reservationService.getAllReservationById(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void deleteReservationById(@PathVariable("id") int id) {
        reservationService.deleteReservationByID();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateReservation(@RequestBody Reservation  reservation) {
        ReservationService.updateReservation(reservation);

    }

    @RequestMapping(method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteReservation(@RequestBody Reservation  reservation) {
        ReservationService.InsertReservation(reservation);
    }
}
