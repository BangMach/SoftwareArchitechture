package com.BangMach.RestaurantReservationService.Engine;

import com.BangMach.RestaurantReservationService.Entity.Reservation;
import com.BangMach.RestaurantReservationService.Service.ReservationServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private ReservationServiceImpl reservationService;

    @KafkaListener(topics = "save_reservation", groupId = "group_id")
    public void save(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Gson gson = gsonBuilder.create();
        Reservation reservation = gson.fromJson(json, Reservation.class);
        logger.info(String.format("#### -> Consumed message -> %s", reservation));

        //save item into database
        reservationService.createReservation(reservation);
        logger.info(String.format("#### -> Finished saving item"));
    }

    @KafkaListener(topics = "put_reservation", groupId = "group_id")
    public void put(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Gson gson = gsonBuilder.create();
        Reservation reservation = gson.fromJson(json, Reservation.class);
        logger.info(String.format("#### -> Consumed message -> %s", reservation));

        //put item into database
        reservationService.updateReservation(reservation);
        logger.info(String.format("#### -> Finished updating item"));
    }
}