package com.BangMach.RestaurantReservationService.Controller;

import com.BangMach.RestaurantReservationService.Entity.Reservation;
import com.BangMach.RestaurantReservationService.engine.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka_reservation")
public class ReservationKafkaController {
    private final Producer producer;

    @Autowired
    ReservationKafkaController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/save")
    public String sendSaveMessageToKafkaTopic(@RequestBody Reservation reservation) {
        this.producer.sendSaveMessage(reservation);
        return "Sent save message";
    }
}
