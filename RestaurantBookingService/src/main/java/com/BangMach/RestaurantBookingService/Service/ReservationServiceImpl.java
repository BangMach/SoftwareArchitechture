package com.BangMach.RestaurantBookingService.Service;

import com.BangMach.RestaurantBookingService.DAO.ReservationDAO;
import com.BangMach.RestaurantBookingService.Entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Service
public class ReservationServiceImpl implements  ReservationServiceInterface {
    private ReservationDAO reservationDAO;


    @Autowired
    public ReservationServiceImpl(@Qualifier("PostgresReservationDAOImpl") ReservationDAO reservationDAO){
        this.reservationDAO = reservationDAO;
    }

    @Override
    @Transactional
    public Collection<Reservation> getAllReservation() {
        return reservationDAO.getAllReservation();
    }

    @Override
    public Reservation getReservationById(int id) {
        return reservationDAO.getReservationById(id);
    }

    @Override
    public void updateReservation(Reservation reservation) {
        reservationDAO.updateReservation(reservation);
    }

    @Override
    public void insertReservation(Reservation reservation) {
        reservationDAO.insertReservation(reservation);
    }

    @Override
    public void deleteReservationByID(int id) {
        reservationDAO.deleteReservation(id);
    }

    @Override
    public List<Reservation> findReservationByName(String name) {
        return reservationDAO.findReservationByName(name);
    }

    @Override
    public List<Reservation> findReservationByPhone(String phone) {
        return reservationDAO.findReservationByPhone(phone);
    }

    @Override
    public List<Reservation> findReservationByDate(Date date) {
        return reservationDAO.findReservationByDate(date);
    }
}
