package com.BangMach.RestaurantBookingService.Service;

import com.BangMach.RestaurantBookingService.DAO.ReservationDAOImpl;
import com.BangMach.RestaurantBookingService.Entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    @Qualifier("HardCodeImpl")
    private ReservationDAOImpl reservationDAOImpl;

    //some new code

    public List<Reservation> getAllReservation() {
        return this.reservationDAOImpl.getAllReservation();
    }

    public Reservation getReservationById(int id) {
        return this.reservationDAOImpl.getReservationById(id);
    }

    public void updateReservation(Reservation reservation) {
        this.reservationDAOImpl.updateReservation(reservation);
    }

    public void insertReservation(Reservation reservation) {
        this.reservationDAOImpl.insertReservation(reservation);
    }

    public void deleteReservationByID(int id) {
        reservationDAOImpl.deleteReservation(id);
    }
}
