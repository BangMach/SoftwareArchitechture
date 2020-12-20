package com.BangMach.RestaurantMenuService.controller;

import com.BangMach.RestaurantMenuService.model.Food;
import com.BangMach.RestaurantMenuService.service.FoodRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodRedisController {

    @Autowired
    private FoodRedisService foodRedisService;

    @PostMapping("/food")
    public Food add(@RequestBody Food food){
        return foodRedisService.saveFood(food);
    }

    @GetMapping("/food")
    public List<Object> getAll() {
        return foodRedisService.getAllFood();
    }

    @GetMapping("/food/{id}")
    public Food getById(@PathVariable("id") int id) {
        return foodRedisService.getFoodById(id);
    }

    @DeleteMapping("/food/{id}")
    public int delete(@PathVariable("id") int id) {
        return foodRedisService.deleteFood(id);
    }
}