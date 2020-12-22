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
                if (dishCategories.contains(dishCategory)) {
                    dish.setCategory(dishCategory);
                } else if (dishCategory.equals("")) {
                    dish.setCategory("na");
                }
            }
            Dish newDish = dishDAO.saveDish(dish);
            if (dish.getCategory().equals("main")) {
                dishRedisRepository.add(newDish);
            }
            return newDish;
        }
        return null;
    }

    @Transactional
    public List<Dish> getAllDish() {
        return dishDAO.getAllDishes();
    }

    @Transactional
    public List<?> findDishByCategory(String category)  {
        if (category.equals("main")) {
            List<Object> redisDishes = dishRedisRepository.getAll();
            if (!redisDishes.isEmpty()) {
                return redisDishes;
            } else {
                List<Dish> dtbDishes = dishDAO.findDishByCategory(category);
                return dishRedisRepository.addDishes(dtbDishes);
            }
        } else {
            return dishDAO.findDishByCategory(category);
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
            if (dish.getCategory() != null) {
                String dishCategory = dish.getCategory().trim().toLowerCase();
                if (dishCategories.contains(dishCategory)) {
                    if (!dishCategory.equals("main")) {
                        dishRedisRepository.delete(id);
                    }
                    currentDish.setCategory(dishCategory);
                } else if (dishCategory.equals("")) {
                    currentDish.setCategory("na");
                    dishRedisRepository.delete(id);
                }
            }
            if (currentDish.getCategory().equals("main")) {
                dishRedisRepository.add(currentDish);
            }
            return dishDAO.saveDish(currentDish);
        } else {
            return null;
        }
    }

    @Transactional
    public String deleteDish(int id) {
        Dish currentDish = dishRedisRepository.getById(id);
        if (currentDish == null) {
            currentDish = dishDAO.findDishById(id);
        }
        if (currentDish != null) {
            if (currentDish.getCategory().equals("main")) {
                dishRedisRepository.delete(id);
            }
            dishDAO.deleteDishById(id);
            return "Deleted dish id " + id;
        } else {
            return "Dish id not found";
        }
    }
}
