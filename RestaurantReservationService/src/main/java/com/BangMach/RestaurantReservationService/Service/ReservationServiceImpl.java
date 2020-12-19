package com.BangMach.RestaurantReservationService.Service;

import com.BangMach.RestaurantReservationService.DAO.ReservationDAO;
import com.BangMach.RestaurantReservationService.Entity.Reservation;
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

    @Override
    public void insertReservation(Reservation reservation) {
        reservationDAO.insertReservation(reservation);
    }
    @Autowired
    public ReservationServiceImpl(@Qualifier("PostgresReservationDAOImpl") ReservationDAO reservationDAO){
        this.reservationDAO = reservationDAO;
    }

    @Override
    public Collection<Reservation> getAllReservation() {
        return reservationDAO.getAllReservation();
    }

    @Override
    public Reservation findReservationById(int id) {
        return reservationDAO.findReservationById(id);
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
    @Transactional
    public List<Reservation> findReservationByDate(Date date) {
        return reservationDAO.findReservationByDate(date);
    }

    @Override
    @Transactional
    public void updateReservation(Reservation  reservation)  {
        Reservation currentReservation = findReservationById(reservation.getId());
        if (currentReservation != null) {
            if (reservation.getName() != null) {
                currentReservation.setName(reservation.getName());
            }
            if (reservation.getEmail() != null) {
                currentReservation.setEmail(reservation.getEmail());
            }
            if (reservation.getNumberOfPeople() != null) {
                currentReservation.setNumberOfPeople(reservation.getNumberOfPeople());
            }
            if (reservation.getStartTime() != null) {
                currentReservation.setEmail(reservation.getEmail());
            }
            if (reservation.getRoomCode() != null) {
                currentReservation.setRoomCode(reservation.getRoomCode());
            }
            if (reservation.getPhone() != null) {
                currentReservation.setPhone(reservation.getPhone());
            }
            reservationDAO.insertReservation(currentReservation);
        }
    }
}
