package com.Restaurant.RestaurantTableService.service;

import com.Restaurant.RestaurantTableService.model.RestaurantTable;
import java.util.List;

public interface TableServiceInterface {

    RestaurantTable createTable(RestaurantTable table);

    List<RestaurantTable> getAllTables(int startAt, int maxResults);

    RestaurantTable findTableById(int id);

    List<RestaurantTable> findTables(RestaurantTable table, int startAt, int maxResults);

    RestaurantTable updateTable(RestaurantTable table);

}
