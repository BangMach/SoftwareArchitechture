package com.BangMach.RestaurantBookingService.DAO;

import com.BangMach.RestaurantBookingService.Entity.Reservation;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface ReservationDAO {

    Collection<Reservation> getAllReservation();

    Reservation findReservationById(int id);

    List<Reservation> findReservationByName(String name);

    List<Reservation> findReservationByPhone(String phone);

    List<Reservation> findReservationByDate(Date date);

    void insertReservation(Reservation reservation);

    void deleteReservation(int id);

}
