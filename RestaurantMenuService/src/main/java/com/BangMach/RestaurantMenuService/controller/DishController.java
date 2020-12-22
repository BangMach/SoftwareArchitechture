package com.BangMach.RestaurantMenuService.controller;

import com.BangMach.RestaurantMenuService.model.Dish;
import com.BangMach.RestaurantMenuService.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity add(@RequestBody Dish dish){
        Dish newDish = dishService.addDish(dish);
        if (newDish == null) {
            return new ResponseEntity<>(
                "Failed to add new dish",
                HttpStatus.BAD_REQUEST
            );
        } else {
            return new ResponseEntity<>(
                newDish,
                HttpStatus.OK
            );
        }
    }

    @GetMapping("/all")
    public List<Dish> getAll() {
        return dishService.getAllDish();
    }

    @GetMapping("/find")
    public List<?> findDishByCategory(@RequestParam String category) {
        return dishService.findDishByCategory(category);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Dish dish) {
        Dish updatedDish = dishService.updateDish(dish);
        if (updatedDish == null) {
            return new ResponseEntity<>(
                "Failed to update dish",
                HttpStatus.BAD_REQUEST
            );
        } else {
            return new ResponseEntity<>(
                updatedDish,
                HttpStatus.OK
            );
        }
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam int id) {
        return dishService.deleteDish(id);
    }
}
