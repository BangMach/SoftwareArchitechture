package com.BangMach.RestaurantReservationService.DAO;

import com.BangMach.RestaurantReservationService.Entity.Reservation;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

public interface ReservationDAO {

    Collection<Reservation> getAllReservation();

    Reservation findReservationById(int id);

    List<Reservation> findReservationByName(String name);

    List<Reservation> findReservationByPhone(String phone);

    List<Reservation> findReservationByDate(Timestamp timestamp);

    Reservation createReservation(Reservation reservation);

    void deleteReservation(int id);

}
