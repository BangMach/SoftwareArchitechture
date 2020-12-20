package com.BangMach.RestaurantMenuService.controller;

import com.BangMach.RestaurantMenuService.model.Dish;
import com.BangMach.RestaurantMenuService.service.DishDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/menu/")
public class DishDatabaseController {

    private DishDatabaseService dishDatabaseService;

    @Autowired
    public DishDatabaseController(DishDatabaseService dishDatabaseService) {
        this.dishDatabaseService = dishDatabaseService;
    }

    @PostMapping("/dtb/food")
    public Dish add(@RequestBody Dish dish){
        return dishDatabaseService.addDish(dish);
    }

    @GetMapping("/dtb/food")
    public List<Dish> getAll() {
        return dishDatabaseService.getAllDish();
    }

    @GetMapping("/dtb/food/{id}")
    public Dish getById(@PathVariable("id") int id) {
        return dishDatabaseService.getDishById(id);
    }

    @PutMapping("/dtb/food")
    public Dish update(@RequestBody Dish dish) {
        return dishDatabaseService.updateDish(dish);
    }

    @DeleteMapping("/dtb/food/{id}")
    public String delete(@PathVariable("id") int id) {
        return dishDatabaseService.deleteDish(id);
    }
}
