package com.BangMach.RestaurantReservationService.engine;

import com.BangMach.RestaurantReservationService.Entity.Reservation;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String SAVE_RESERVATION = "save_reservation";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendSaveMessage(Reservation reservation) {
        logger.info(String.format("#### -> Producing message -> %s", reservation));
        Gson gson = new Gson();
        String json = gson.toJson(reservation);

        this.kafkaTemplate.send(SAVE_RESERVATION, json);
    }
}
