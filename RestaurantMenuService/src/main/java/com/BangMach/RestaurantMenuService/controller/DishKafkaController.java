package com.BangMach.RestaurantMenuService.controller;

import com.BangMach.RestaurantMenuService.engine.Producer;
import com.BangMach.RestaurantMenuService.model.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class DishKafkaController {
    private final Producer producer;

    @Autowired
    DishKafkaController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/save")
    public String sendSaveMessageToKafkaTopic(@RequestBody Dish dish) {
        this.producer.sendSaveMessage(dish);
        return "Sent save message";
    }

    @GetMapping(value = "/get/{id}")
    public String sendGetByIdMessageToKafkaTopic(@PathVariable int id) {
        this.producer.sendGetByIdMessage(id);
        return  "Sent get by Id message";
    }

    @DeleteMapping(value = "/del/{id}")
    public String sendDelMessageToKafkaTopic(@PathVariable int id) {
        this.producer.sendDelMessage(id);
        return  "Sent delete by Id message";
    }
}
