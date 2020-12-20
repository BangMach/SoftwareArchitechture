package com.BangMach.RestaurantMenuService.service;

import com.BangMach.RestaurantMenuService.model.Dish;
import com.BangMach.RestaurantMenuService.repository.DishDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class DishDatabaseService {

    private DishDatabaseRepository dishDatabaseRepository;

    @Autowired
    public DishDatabaseService(DishDatabaseRepository dishDatabaseRepository) {
        this.dishDatabaseRepository = dishDatabaseRepository;
    }

    @Transactional
    public Dish addDish(Dish dish) {
        String dishName = dish.getName();
        if (dishName != null && !dishName.equals("")) {
            String dishCategory = "na";
            if (dish.getCategory() != null) {
                if (Arrays.asList("main", "appetizer", "dessert", "na").contains(dish.getCategory().trim().toLowerCase())) {
                    dishCategory = dish.getCategory().trim().toLowerCase();
                }
            }
            dish.setCategory(dishCategory);
            dishDatabaseRepository.save(dish);
            return dish;
        }
        return null;
    }

    @Transactional
    public List<Dish> getAllDish() {
        return (List<Dish>) dishDatabaseRepository.findAll();
    }

    @Transactional
    public Dish getDishById(int id) {
        return dishDatabaseRepository.findById(id).get();
    }

    @Transactional
    public Dish updateDish(Dish dish) {
        try {
            Dish currentDish = getDishById(dish.getId());
            String dishName = dish.getName();
            if (dishName != null && !dishName.equals("")) {
                currentDish.setName(dishName);
            }
            String dishCategory = dish.getCategory();
            if (dishCategory != null) {
                if (Arrays.asList("main", "appetizer", "dessert", "na").contains(dishCategory.trim().toLowerCase())) {
                    currentDish.setCategory(dishCategory.trim().toLowerCase());
                } else if (dishCategory.equals("")) {
                    currentDish.setCategory("na");
                }
            }
            String description = dish.getDescription();
            if ( description != null) {
                currentDish.setDescription(description);
            }
            String imagePath = dish.getImagePath();
            if (imagePath != null) {
                currentDish.setImagePath(imagePath);
            }
            dishDatabaseRepository.save(currentDish);
            return currentDish;
        } catch(Exception e) {
            return null;
        }
    }

    @Transactional
    public String deleteDish(int id) {
        try {
            dishDatabaseRepository.delete(getDishById(id));
            return "Delete dish id " + id;
        } catch (Exception e) {
            return "No dish found";
        }
    }
}
