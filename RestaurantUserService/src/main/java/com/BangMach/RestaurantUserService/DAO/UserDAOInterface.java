package com.BangMach.RestaurantUserService.DAO;

import com.BangMach.RestaurantUserService.model.ReservationDetail;
import com.BangMach.RestaurantUserService.model.RestaurantTable;
import java.sql.Timestamp;
import java.util.List;

public interface UserDAOInterface {

    List<RestaurantTable> searchAvailableTables(Timestamp startTime);

    List<ReservationDetail> findReservationDetails(ReservationDetail reservationDetail, int startAt, int maxResults);

    List<ReservationDetail> getAllReservationDetails(int startAt, int maxResults);
}
