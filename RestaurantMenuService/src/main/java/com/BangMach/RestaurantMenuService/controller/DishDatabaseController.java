package com.BangMach.RestaurantMenuService.controller;

import com.BangMach.RestaurantMenuService.model.Dish;
import com.BangMach.RestaurantMenuService.service.DishDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/menu/dtb")
public class DishDatabaseController {

    private final DishDatabaseService dishDatabaseService;

    @Autowired
    public DishDatabaseController(DishDatabaseService dishDatabaseService) {
        this.dishDatabaseService = dishDatabaseService;
    }

    @PostMapping("/add")
    public Dish add(@RequestBody Dish dish){
        return dishDatabaseService.addDish(dish);
    }

    @GetMapping("/all")
    public List<Dish> getAll() {
        return dishDatabaseService.getAllDish();
    }

    @GetMapping("/find")
    public Dish getById(@RequestParam int id) {
        return dishDatabaseService.getDishById(id);
    }

    @PutMapping("/update")
    public Dish update(@RequestBody Dish dish) {
        return dishDatabaseService.updateDish(dish);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        return dishDatabaseService.deleteDish(id);
    }
}
