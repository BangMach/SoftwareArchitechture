package com.BangMach.RestaurantReservationService.Service;

import com.BangMach.RestaurantReservationService.Entity.Reservation;

import java.util.Collection;
import java.sql.Timestamp;
import java.util.List;

public interface ReservationServiceInterface {

    Collection<Reservation> getAllReservation();

    Reservation findReservationById(int id);

    Reservation updateReservation(Reservation reservation);

    Reservation createReservation(Reservation reservation);

    void deleteReservationByID(int id);

    List<Reservation> findReservationByName(String name);

    List<Reservation> findReservationByPhone(String phone);

    List<Reservation> findReservationByDate(Timestamp timestamp);

}
