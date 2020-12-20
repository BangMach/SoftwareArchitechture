package com.BangMach.RestaurantService.repository;

import com.BangMach.RestaurantService.model.Food;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface FoodDatabaseRepository extends CrudRepository<Food, Integer>{
}
