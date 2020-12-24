package com.BangMach.RestaurantMenuService.DAO;

import com.BangMach.RestaurantMenuService.model.Dish;

import java.util.List;

public interface DishDAOInterface {

    List<Dish> getAllDishes(int startAt, int maxResults);

    Dish findDishById(int id);

    Dish saveDish(Dish dish);

    void deleteDishById(int id);

    List<Dish> findDishByCategory(String category, int startAt, int maxResults);

    List<Dish> findDishes(Dish dish, int startAt, int maxResults);

}
