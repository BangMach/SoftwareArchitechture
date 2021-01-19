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
@CrossOrigin(origins ="http://localhost:3000")
public class DishController {

    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping("")
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

    @GetMapping("")
    public List<Dish> getAll(@RequestParam(value= "startAt", defaultValue = "0") Integer startAt, @RequestParam(value= "maxResults", defaultValue = "50") Integer maxResults) {
        return dishService.getAllDish(startAt, maxResults);
    }

    @PutMapping("")
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

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        return dishService.deleteDish(id);
    }

    @GetMapping("/{category}")
    public List<?> findDishByCategory(@PathVariable String category, @RequestParam(value= "startAt", defaultValue = "0") Integer startAt, @RequestParam(value= "maxResults", defaultValue = "50") Integer maxResults) {
        return dishService.findDishByCategory(category, startAt, maxResults);
    }

    @PostMapping(value = "/attributes")
    public List<Dish> findDishes(@RequestBody Dish dish, @RequestParam(value= "startAt", defaultValue = "0") Integer startAt, @RequestParam(value= "maxResults", defaultValue = "50") Integer maxResults) {
        return dishService.findDishes(dish, startAt, maxResults);
    }

    @PostMapping("/populate")
    public String populateData() {
        dishService.populateData();
        return "Done";
    }
}
