package com.BangMach.RestaurantBookingService.Controller;


import com.BangMach.RestaurantBookingService.Entity.Reservation;
import com.BangMach.RestaurantBookingService.Service.ReservationService;
import com.BangMach.RestaurantBookingService.Service.ReservationServiceImpl;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Collection;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationServiceImpl reservationService;
    public ReservationController() {
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Collection<Reservation> getAllReservation() {
        return reservationService.getAllReservation();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void deleteReservationById(@PathVariable("id") int id) {
        reservationService.deleteReservationByID(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateReservation(@RequestBody Reservation  reservation) {
        reservationService.updateReservation(reservation);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public void addReservation(@RequestBody Reservation reservation) throws InvalidKeySpecException, NoSuchAlgorithmException {
        reservationService.insertReservation(reservation);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void updateAccount(@RequestBody Reservation reservation) throws InvalidKeySpecException, NoSuchAlgorithmException {
        reservationService.updateReservation(reservation);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteAccount(@RequestParam Integer id){
        reservationService.deleteReservationByID(id);
    }


}
