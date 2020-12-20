package com.BangMach.RestaurantMenuService.controller;

import com.BangMach.RestaurantMenuService.model.Dish;
import com.BangMach.RestaurantMenuService.service.DishRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/menu/redis")
public class DishRedisController {

    private final DishRedisService dishRedisService;

    @Autowired
    public DishRedisController(DishRedisService dishRedisService) {
        this.dishRedisService = dishRedisService;
    }

    @PostMapping("/add")
    public Dish add(@RequestBody Dish dish){
        return dishRedisService.saveDish(dish);
    }

    @GetMapping("/all")
    public List<Object> getAll() {
        return dishRedisService.getAllDish();
    }

    @GetMapping("/find")
    public Dish getById(@RequestParam int id) {
        return dishRedisService.getDishById(id);
    }

    @DeleteMapping("/delete")
    public int delete(@RequestParam int id) {
        return dishRedisService.deleteDish(id);
    }
}