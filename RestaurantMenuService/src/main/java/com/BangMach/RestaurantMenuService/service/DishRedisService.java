package com.BangMach.RestaurantMenuService.service;

import com.BangMach.RestaurantMenuService.model.Dish;
import com.BangMach.RestaurantMenuService.repository.DishRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DishRedisService {

    private DishRedisRepository dishRedisRepository;

    @Autowired
    public DishRedisService(DishRedisRepository dishRedisRepository) {
        this.dishRedisRepository = dishRedisRepository;
    }

    public Dish saveDish(Dish dish) {
        return dishRedisRepository.add(dish);
    }

    public List<Object> getAllDish() {
        return dishRedisRepository.getAll();
    }

    public Dish getDishById(int id) {
        return dishRedisRepository.getById(id);
    }

    public int deleteDish(int id) {
        return dishRedisRepository.delete(id);
    }
}