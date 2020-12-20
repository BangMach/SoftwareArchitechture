package com.BangMach.RestaurantMenuService.repository;

import com.BangMach.RestaurantMenuService.model.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DishRedisRepository {
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String KEY = "FOOD";

    public Dish add(Dish dish) {
        redisTemplate.opsForHash().put(KEY, Integer.toString(dish.getId()), dish);
		return dish;
    }
    
    public List<Object> getAll() {
        List<Object> food = redisTemplate.opsForHash().values(KEY);
        return food;
    }

    public Dish getById(int id) {
        Dish dish = (Dish) redisTemplate.opsForHash().get(KEY, Integer.toString(id));
        return dish;
    }

    public int delete(int id) {
        redisTemplate.opsForHash().delete(KEY, Integer.toString(id));
        return id;
    }
}