package com.Restaurant.RestaurantTableService.DAO;

import com.Restaurant.RestaurantTableService.model.RestaurantTable;
import java.util.List;

public interface TableDAOInterface {

    List<RestaurantTable> getAllTables(int startAt, int maxResults);

    RestaurantTable findTableById(int id);

    List<RestaurantTable> findTables(RestaurantTable account, int startAt, int maxResults);

    RestaurantTable saveTable(RestaurantTable account);

}
