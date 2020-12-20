package com.BangMach.RestaurantService.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.BangMach.RestaurantService.model.Food;

@Repository
public class FoodRedisRepository {
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String KEY = "FOOD";

    public Food add(Food food) {
        redisTemplate.opsForHash().put(KEY, Integer.toString(food.getId()), food);
		return food;
    }
    
    public List<Object> getAll() {
        List<Object> food = redisTemplate.opsForHash().values(KEY);
        return food;
    }

    public Food getById(int id) {
        Food food = (Food) redisTemplate.opsForHash().get(KEY, Integer.toString(id));
        return food;
    }

    public int delete(int id) {
        redisTemplate.opsForHash().delete(KEY, Integer.toString(id));
        return id;
    }
}