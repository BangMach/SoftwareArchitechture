package com.BangMach.RestaurantBookingService.DAO;

import com.BangMach.RestaurantBookingService.Entity.Reservation;

import java.util.List;

public interface ReservationDAO {
    void deleteReservation(int id);

    List<Reservation> getAllReservation();

    Reservation getReservationById(int id);

    void updateReservation(Reservation reservation);

    void insertReservation(Reservation reservation);
}
