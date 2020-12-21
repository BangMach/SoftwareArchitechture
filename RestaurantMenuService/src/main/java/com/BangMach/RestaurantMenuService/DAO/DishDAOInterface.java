package com.BangMach.RestaurantMenuService.DAO;

import com.BangMach.RestaurantMenuService.model.Dish;

import java.util.List;

public interface DishDAOInterface {

    List<Dish> getAllDishes();

    Dish findDishById(int id);

    Dish saveDish(Dish dish);

    void deleteDishById(int id);

    List<Dish> findDishByCategory(String category);

}
