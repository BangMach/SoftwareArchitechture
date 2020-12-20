package com.BangMach.RestaurantMenuService.repository;

import com.BangMach.RestaurantMenuService.model.Dish;
import org.springframework.data.repository.CrudRepository;

public interface DishDatabaseRepository extends CrudRepository<Dish, Integer>{

}
