package com.BangMach.RestaurantReservationService.Service;

import com.BangMach.RestaurantReservationService.Entity.Reservation;

import java.util.Collection;

public interface ReservationServiceInterface {

    Collection<Reservation> getAllReservation();

    Reservation findReservationById(int id);

    Reservation updateReservation(Reservation reservation);

    Reservation createReservation(Reservation reservation);

    String deleteReservationByID(int id);

}
