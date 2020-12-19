package com.Restaurant.RestaurantTableService.DAO;

import com.Restaurant.RestaurantTableService.model.RestaurantTable;
import java.util.List;

public interface TableDAOInterface {

    List<RestaurantTable> getAllTables();

    RestaurantTable findTableById(int id);

    List<RestaurantTable> findTables(RestaurantTable account);

    RestaurantTable saveTable(RestaurantTable account);

    void deleteTableById(int id);
}
