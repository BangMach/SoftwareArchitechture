package com.BangMach.RestaurantMenuService.service;

import com.BangMach.RestaurantMenuService.DAO.DishDAOImpl;
import com.BangMach.RestaurantMenuService.model.Dish;
import com.BangMach.RestaurantMenuService.repository.DishRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class DishService {

    private final DishDAOImpl dishDAO;
    private final DishRedisRepository dishRedisRepository;
    private final List<String> dishCategories = Arrays.asList("main", "appetizer", "dessert", "na");

    @Autowired
    public DishService(@Qualifier("dishDAOImpl") DishDAOImpl dishDAO, DishRedisRepository dishRedisRepository) {
        this.dishDAO = dishDAO;
        this.dishRedisRepository = dishRedisRepository;
    }

    @Transactional
    public Dish addDish(Dish dish) {
        String dishName = dish.getName();
        if (dishName != null && !dishName.equals("")) {
            if (dish.getCategory() != null) {
                String dishCategory = dish.getCategory().trim().toLowerCase();
                if (dishCategory.equals("")) {
                    dishCategory = "na";
                }
                if (dishCategories.contains(dishCategory)) {
                    dish.setCategory(dishCategory);
                    Dish newDish = dishDAO.saveDish(dish);
                    if (newDish.getCategory().equals("main")) {
                        dishRedisRepository.add(newDish);
                    }
                    return newDish;
                }
            }
        }
        return null;
    }

    @Transactional
    public List<Dish> getAllDish(int startAt, int maxResults) {
        return dishDAO.getAllDishes(startAt, maxResults);
    }

    @Transactional
    public List<?> findDishByCategory(String category, int startAt, int maxResults)  {
        if (category.equals("main")) {
            List<Object> redisDishes = dishRedisRepository.getAll();
            int redisSize = redisDishes.size();
            if (redisSize >= startAt + maxResults) {
                return redisDishes.subList(startAt, startAt + maxResults);
            } else if (redisSize < startAt) {
                List<Dish> dtbDishes = dishDAO.findDishByCategory(category, startAt, maxResults);
                dishRedisRepository.addDishes(dtbDishes);
                return dtbDishes;
            } else {
                List<Dish> dtbDishes = dishDAO.findDishByCategory(category, redisSize, startAt + maxResults - redisSize);
                redisDishes = redisDishes.subList(startAt, redisSize);
                redisDishes.addAll(dtbDishes);
                dishRedisRepository.addDishes(dtbDishes);
                return redisDishes;
            }
        } else {
            return dishDAO.findDishByCategory(category, startAt, maxResults);
        }
    }

    @Transactional
    public Dish updateDish(Dish dish) {
        int id = dish.getId();
        Dish currentDish = dishRedisRepository.getById(id);
        if (currentDish == null) {
            currentDish = dishDAO.findDishById(id);
        }
        if (currentDish != null) {
            String dishName = dish.getName();
            if (dishName != null && !dishName.equals("")) {
                currentDish.setName(dishName);
            }
            String description = dish.getDescription();
            if ( description != null) {
                currentDish.setDescription(description);
            }
            String imagePath = dish.getImagePath();
            if (imagePath != null) {
                currentDish.setImagePath(imagePath);
            }
            return dishDAO.saveDish(currentDish);
        } else {
            return null;
        }
    }

    @Transactional
    public String deleteDish(int id) {
        Dish currentDish = dishRedisRepository.getById(id);
        dishRedisRepository.delete(id);
        if (currentDish == null) {
            currentDish = dishDAO.findDishById(id);
        }
        if (currentDish != null) {
            dishDAO.deleteDishById(id);
            return "Deleted dish id " + id;
        } else {
            return "Dish id not found";
        }
    }

    @Transactional
    public List<Dish> findDishes(Dish dish, int startAt, int maxResults) {
        return dishDAO.findDishes(dish, startAt, maxResults);
    }
}
