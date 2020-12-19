package com.BangMach.RestaurantService.service;

import com.BangMach.RestaurantService.model.Food;
import com.BangMach.RestaurantService.repository.FoodDatabaseRepository;
import com.BangMach.RestaurantService.repository.FoodDatabaseRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FoodDatabaseService {

    @Autowired
    private FoodDatabaseRepository foodDatabaseRepository;

    @Autowired
    public FoodDatabaseService(FoodDatabaseRepository foodDatabaseRepository) {
        this.foodDatabaseRepository = foodDatabaseRepository;
    }

    @Transactional
    public Food addFood(Food food) {
        foodDatabaseRepository.save(food);
        return food;
    }

    @Transactional
    public List<Food> getAllFood() {
        return (List<Food>) foodDatabaseRepository.findAll();
    }

    @Transactional
    public Food getFoodById(int id) {
        return (Food) foodDatabaseRepository.findById(id).get();
    }

    @Transactional
    public int deleteFood(int id) {
        foodDatabaseRepository.delete(getFoodById(id));
        return id;
    }
}
