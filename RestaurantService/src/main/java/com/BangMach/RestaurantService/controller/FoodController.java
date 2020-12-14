package com.BangMach.RestaurantService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.BangMach.RestaurantService.model.Food;
import com.BangMach.RestaurantService.service.FoodService;

@RestController
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping("/food")
    public Food add(@RequestBody Food food){
        return foodService.saveFood(food);
    }

    @GetMapping("/food")
    public List<Food> getAll(){
        return foodService.getAllFood();
    }

    @GetMapping("/food/{id}")
    public Food getById(@PathVariable("id") int id) {
        return foodService.getFoodById(id);
    }

    @DeleteMapping("/food/{id}")
    public int delete(@PathVariable("id") int id) {
        return foodService.deleteFood(id);
    }
}