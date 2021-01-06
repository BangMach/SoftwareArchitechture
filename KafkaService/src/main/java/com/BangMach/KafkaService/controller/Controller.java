package com.BangMach.KafkaService.controller;

import com.BangMach.KafkaService.engine.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class Controller {
    private final Producer producer;

    @Autowired
    Controller(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/reservation/save")
    public <T> T sendSaveMessageToKafkaTopic(@RequestBody T t) {
        this.producer.sendSaveMessage(t);
        return t;
    }

    @PostMapping(value = "/reservation/put")
    public <T> T sendPutMessageToKafkaTopic(@RequestBody T t) {
        this.producer.sendPutMessage(t);
        return t;
    }
}