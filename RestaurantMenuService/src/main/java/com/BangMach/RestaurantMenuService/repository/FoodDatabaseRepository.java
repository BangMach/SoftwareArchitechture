package com.BangMach.RestaurantMenuService.repository;

import com.BangMach.RestaurantMenuService.model.Food;
import org.springframework.data.repository.CrudRepository;

public interface FoodDatabaseRepository extends CrudRepository<Food, Integer>{
}
