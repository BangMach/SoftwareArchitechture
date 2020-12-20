package com.BangMach.RestaurantUserService.DAO;

import com.BangMach.RestaurantUserService.model.RestaurantTable;
import java.sql.Timestamp;
import java.util.List;

public interface UserDAOInterface {

    List<RestaurantTable> searchAvailableTable(Timestamp startTime);

}
