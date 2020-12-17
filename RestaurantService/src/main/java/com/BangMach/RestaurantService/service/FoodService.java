package com.BangMach.RestaurantService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.BangMach.RestaurantService.model.Food;
import com.BangMach.RestaurantService.repository.FoodRepository;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public Food saveFood(Food food) {
        return foodRepository.add(food);
    }

    public List<Object> getAllFood() {
        return foodRepository.getAll();
    }

    public Food getFoodById(int id) {
        return foodRepository.getById(id);
    }

    public int deleteFood(int id) {
        return foodRepository.delete(id);
    }
}