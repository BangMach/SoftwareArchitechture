package com.Restaurant.RestaurantTableService.service;

import com.Restaurant.RestaurantTableService.model.RestaurantTable;
import java.util.List;

public interface TableServiceInterface {

    RestaurantTable createTable(RestaurantTable table);

    List<RestaurantTable> getAllTables();

    RestaurantTable findTableById(int id);

    List<RestaurantTable> findTables(RestaurantTable table);

    RestaurantTable updateTable(RestaurantTable table);

    String deleteTableById(int id);
}
