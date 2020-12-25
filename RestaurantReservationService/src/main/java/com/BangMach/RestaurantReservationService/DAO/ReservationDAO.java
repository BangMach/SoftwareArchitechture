package com.BangMach.RestaurantReservationService.DAO;

import com.BangMach.RestaurantReservationService.Entity.Reservation;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

public interface ReservationDAO {

    Reservation findReservationById(int id);

    Reservation createReservation(Reservation reservation);

    void deleteReservation(int id);

}
