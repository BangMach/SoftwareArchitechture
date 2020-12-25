package com.BangMach.RestaurantMenuService.repository;

import com.BangMach.RestaurantMenuService.model.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class DishRedisRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String KEY = "DISH";
    private static int maximumRecords = 10;

    @Autowired
    public DishRedisRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.redisTemplate.expire(KEY, 30, TimeUnit.DAYS);
    }

    public Dish add(Dish dish) {
        if (redisTemplate.opsForHash().size(KEY) < maximumRecords) {
            redisTemplate.opsForHash().put(KEY, Integer.toString(dish.getId()), dish);
        }
		return dish;
    }

    public List<Object> addDishes(List<Dish> dishes) {
        dishes.forEach(this::add);
        return getAll();
    }
    
    public List<Object> getAll() {
        return redisTemplate.opsForHash().values(KEY);
    }

    public Dish getById(int id) {
        return (Dish) redisTemplate.opsForHash().get(KEY, Integer.toString(id));
    }

    public void delete(int id) {
        redisTemplate.opsForHash().delete(KEY, Integer.toString(id));
    }
}