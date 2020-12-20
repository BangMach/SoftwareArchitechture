package com.BangMach.RestaurantUserService.service;

import com.BangMach.RestaurantUserService.model.Reservation;
import com.BangMach.RestaurantUserService.model.ReservationDetail;
import com.BangMach.RestaurantUserService.model.RestaurantTable;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.List;

public interface UserServiceInterface {

    List<RestaurantTable> searchAvailableTables(Timestamp startTime);

    List<ReservationDetail> getAll();

    Reservation createReservation(Reservation reservation);

    ResponseEntity<Reservation> updateReservation(Reservation reservation);

}
