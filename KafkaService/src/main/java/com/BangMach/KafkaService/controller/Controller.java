package com.BangMach.KafkaService.controller;

import com.BangMach.KafkaService.engine.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka_reservation")
public class Controller {
    private final Producer producer;

    @Autowired
    Controller(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/save")
    public <T> String sendSaveMessageToKafkaTopic(@RequestBody T t) {
        this.producer.sendSaveMessage(t);
        return "Sent save message";
    }
}
