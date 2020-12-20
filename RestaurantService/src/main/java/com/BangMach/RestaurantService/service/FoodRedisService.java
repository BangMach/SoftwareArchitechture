package com.BangMach.RestaurantService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.BangMach.RestaurantService.model.Food;
import com.BangMach.RestaurantService.repository.FoodRedisRepository;

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