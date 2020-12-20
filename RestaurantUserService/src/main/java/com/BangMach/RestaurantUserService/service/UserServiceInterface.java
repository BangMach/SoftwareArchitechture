package com.BangMach.RestaurantUserService.service;

import com.BangMach.RestaurantUserService.model.RestaurantTable;
import java.sql.Timestamp;
import java.util.List;

public interface UserServiceInterface {

    List<RestaurantTable> searchAvailableTables(Timestamp startTime);

}
