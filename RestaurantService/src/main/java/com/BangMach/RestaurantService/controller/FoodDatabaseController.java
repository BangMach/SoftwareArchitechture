package com.BangMach.RestaurantService.controller;

import com.BangMach.RestaurantService.model.Food;
import com.BangMach.RestaurantService.service.FoodDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/menu/")
public class FoodDatabaseController {

    private FoodDatabaseService foodDatabaseService;

    @Autowired
    public FoodDatabaseController(FoodDatabaseService foodDatabaseService) {
        this.foodDatabaseService = foodDatabaseService;
    }

    @PostMapping("/dtb/food")
    public Food add(@RequestBody Food food){
        return foodDatabaseService.addFood(food);
    }

    @GetMapping("/dtb/food")
    public List<Food> getAll() {
        return foodDatabaseService.getAllFood();
    }

    @GetMapping("/dtb/food/{id}")
    public Food getById(@PathVariable("id") int id) {
        return foodDatabaseService.getFoodById(id);
    }

    @PutMapping("/dtb/food")
    public Food update(@RequestBody Food food) {
        return foodDatabaseService.updateFood(food);
    }

    @DeleteMapping("/dtb/food/{id}")
    public int delete(@PathVariable("id") int id) {
        return foodDatabaseService.deleteFood(id);
    }
}
