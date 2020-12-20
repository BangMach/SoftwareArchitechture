package com.BangMach.RestaurantMenuService.controller;

import com.BangMach.RestaurantMenuService.model.Dish;
import com.BangMach.RestaurantMenuService.service.DishRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DishRedisController {

    @Autowired
    private DishRedisService dishRedisService;

    @PostMapping("/food")
    public Dish add(@RequestBody Dish dish){
        return dishRedisService.saveDish(dish);
    }

    @GetMapping("/food")
    public List<Object> getAll() {
        return dishRedisService.getAllDish();
    }

    @GetMapping("/food/{id}")
    public Dish getById(@PathVariable("id") int id) {
        return dishRedisService.getDishById(id);
    }

    @DeleteMapping("/food/{id}")
    public int delete(@PathVariable("id") int id) {
        return dishRedisService.deleteDish(id);
    }
}