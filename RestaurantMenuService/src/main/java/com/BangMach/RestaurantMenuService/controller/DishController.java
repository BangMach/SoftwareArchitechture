package com.BangMach.RestaurantMenuService.controller;

import com.BangMach.RestaurantMenuService.model.Dish;
import com.BangMach.RestaurantMenuService.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/menu")
public class DishController {

    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping("/add")
    public Dish add(@RequestBody Dish dish){
        return dishService.addDish(dish);
    }

    @GetMapping("/all")
    public List<Dish> getAll() {
        return dishService.getAllDish();
    }

    @GetMapping("/find/category")
    public List<?> findDishByCategory(@RequestParam String category) {
        return dishService.findDishByCategory(category);
    }

    @PutMapping("/update")
    public Dish update(@RequestBody Dish dish) {
        return dishService.updateDish(dish);
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam int id) {
        return dishService.deleteDish(id);
    }
}
