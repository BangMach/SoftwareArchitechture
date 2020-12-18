package com.BangMach.RestaurantBookingService.Controller;


import com.BangMach.RestaurantBookingService.Entity.Reservation;
import com.BangMach.RestaurantBookingService.Service.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private ReservationServiceImpl reservationService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public void getAllReservation() {

        System.out.println("system print completely");
    }

}
