package com.BangMach.RestaurantMenuService.service;

import com.BangMach.RestaurantMenuService.model.Food;
import com.BangMach.RestaurantMenuService.repository.FoodRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodRedisService {

    @Autowired
    private FoodRedisRepository foodRedisRepository;

    public Food saveFood(Food food) {
        return foodRedisRepository.add(food);
    }

    public List<Object> getAllFood() {
        return foodRedisRepository.getAll();
    }

    public Food getFoodById(int id) {
        return foodRedisRepository.getById(id);
    }

    public int deleteFood(int id) {
        return foodRedisRepository.delete(id);
    }
}