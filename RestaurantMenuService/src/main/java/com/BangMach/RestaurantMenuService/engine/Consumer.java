package com.BangMach.RestaurantMenuService.engine;

import com.BangMach.RestaurantMenuService.DAO.DishDAOImpl;
import com.BangMach.RestaurantMenuService.model.Dish;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private DishDAOImpl dishDAO;

    @KafkaListener(topics = "save", groupId = "group_id")
    public void save(String json) throws IOException {
        Gson gson = new Gson();
        Dish dish = gson.fromJson(json, Dish.class);
        logger.info(String.format("#### -> Consumed message -> %s", dish));

        //save item into database
        dishDAO.saveDish(dish);
        logger.info(String.format("#### -> Finished saving item"));
    }

    @KafkaListener(topics = "get", groupId = "group_id")
    public void getById(String id) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", id));

        //get item from database
        Dish dish = dishDAO.findDishById(Integer.parseInt(id));
        logger.info(String.format("#### -> ID message -> %s", dish));
        logger.info(String.format("#### -> Finished getting item"));
    }

    @KafkaListener(topics = "del", groupId = "group_id")
    public void del(String id) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", id));

        //delete item from database
        dishDAO.deleteDishById(Integer.parseInt(id));
        logger.info(String.format("#### -> Finished deleting item"));
    }
}
