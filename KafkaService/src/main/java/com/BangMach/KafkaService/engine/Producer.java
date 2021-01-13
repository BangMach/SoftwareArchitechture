package com.BangMach.KafkaService.engine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String SAVE_RESERVATION = "save_reservation";
    private static final String PUT_RESERVATION = "put_reservation";
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public <T> void sendSaveMessage(T t) {
        String json = parseJson(t);
        this.kafkaTemplate.send(SAVE_RESERVATION, json);
    }

    public <T> void sendPutMessage(T t) {
        String json = parseJson(t);
        this.kafkaTemplate.send(PUT_RESERVATION, json);
    }

    public <T> String parseJson(T t) {
        logger.info(String.format("#### -> Producing message -> %s", t));
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Gson gson = gsonBuilder.create();
        return gson.toJson(t);
    }

}