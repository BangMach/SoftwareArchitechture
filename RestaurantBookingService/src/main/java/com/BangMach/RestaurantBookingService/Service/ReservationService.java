package com.BangMach.RestaurantBookingService.Service;

import com.BangMach.RestaurantBookingService.DAO.ReservationDAOImpl;
import com.BangMach.RestaurantBookingService.Entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservationService implements ReservationServiceInterface {

    @Autowired
    @Qualifier("HardCodeImpl")
    private ReservationDAOImpl reservationDAOImpl;

    //some new code

    @Override
    public List<Reservation> getAllReservation() {
        return (List<Reservation>) this.reservationDAOImpl.getAllReservation();
    }

    @Override
    public Reservation getReservationById(int id) {
        return this.reservationDAOImpl.getReservationById(id);
    }

    @Override
    public void updateReservation(Reservation reservation) {
        this.reservationDAOImpl.updateReservation(reservation);
    }

    @Override
    public void insertReservation(Reservation reservation) {
        this.reservationDAOImpl.insertReservation(reservation);
    }

    @Override
    public void deleteReservationByID(int id) {
        reservationDAOImpl.deleteReservation(id);
    }

    @Override
    public List<Reservation> findReservationByName(String name) {
        return null;
    }

    @Override
    public List<Reservation> findReservationByPhone(String phone) {
        return null;
    }

    @Override
    public List<Reservation> findReservationByDate(Date date) {
        return null;
    }
}
