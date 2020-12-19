package com.BangMach.RestaurantBookingService.Service;

import com.BangMach.RestaurantBookingService.Entity.Reservation;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface ReservationServiceInterface {
    Collection<Reservation> getAllReservation();

    Reservation findReservationById(int id);

    void updateReservation(Reservation reservation);

    void insertReservation(Reservation reservation);

    void deleteReservationByID(int id);

    List<Reservation> findReservationByName(String name);

    List<Reservation> findReservationByPhone(String phone);

    List<Reservation> findReservationByDate(Date date);

}
