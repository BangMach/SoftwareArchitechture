package com.BangMach.RestaurantUserService.engine;

import com.BangMach.RestaurantUserService.model.Reservation;
import com.BangMach.RestaurantUserService.service.UserServiceImpl;
import com.google.gson.Gson;
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
    private UserServiceImpl userService;

    @KafkaListener(topics = "save_reservation", groupId = "group_id")
    public void save(String json) throws IOException {
        Gson gson = new Gson();
        Reservation reservation = gson.fromJson(json, Reservation.class);
        logger.info(String.format("#### -> Consumed message -> %s", reservation));

        //save item into database
        userService.createReservation(reservation);
        logger.info(String.format("#### -> Finished saving item"));
    }
}
