package com.BangMach.KafkaService.engine;

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

    public <T> void sendSaveMessage(T t) {
        logger.info(String.format("#### -> Producing message -> %s", t));
        Gson gson = new Gson();
        String json = gson.toJson(t);

        this.kafkaTemplate.send(SAVE_RESERVATION, json);
    }
}
