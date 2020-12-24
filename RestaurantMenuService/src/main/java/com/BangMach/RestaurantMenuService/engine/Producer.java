package com.BangMach.RestaurantMenuService.engine;

import com.BangMach.RestaurantMenuService.model.Dish;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String SAVE = "save";
    private static final String GET = "get";
    private static final String DEL = "del";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendSaveMessage(Dish dish) {
        logger.info(String.format("#### -> Producing message -> %s", dish));
        Gson gson = new Gson();
        String json = gson.toJson(dish);

        this.kafkaTemplate.send(SAVE, json);
    }

    public void sendGetByIdMessage(int id) {
        logger.info(String.format("#### -> Producing message -> %s", id));

        this.kafkaTemplate.send(GET, Integer.toString(id));
    }

    public void sendDelMessage(int id) {
        logger.info(String.format("#### -> Producing message -> %s", id));

        this.kafkaTemplate.send(DEL, Integer.toString(id));
    }
}
