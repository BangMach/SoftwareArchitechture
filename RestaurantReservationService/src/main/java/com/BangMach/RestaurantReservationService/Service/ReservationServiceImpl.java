package com.BangMach.RestaurantReservationService.Service;

import com.BangMach.RestaurantReservationService.DAO.ReservationDAO;
import com.BangMach.RestaurantReservationService.Entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;

@Service
public class ReservationServiceImpl implements  ReservationServiceInterface {

    private final ReservationDAO reservationDAO;

    @Autowired
    public ReservationServiceImpl(@Qualifier("ReservationDAOImpl") ReservationDAO reservationDAO){
        this.reservationDAO = reservationDAO;
    }

    @Override
    @Transactional
    public Reservation createReservation(Reservation reservation) {
        String name = reservation.getName();
        if (name != null && !name.equals("")) {
            String phone = reservation.getPhone();
                if (phone != null && !phone.equals("")) {
                    Timestamp timestamp = reservation.getStartTime();
                    if (timestamp != null) {
                        reservation.setStartTime(new Timestamp(timestamp.getTime() - 1000*60*60*7));
                        if (reservation.getTableId() != 0) {
                            String status = "booked";
                            if (reservation.getStatus() != null) {
                                if (Arrays.asList("booked", "cancelled", "done").contains(reservation.getStatus().trim().toLowerCase())) {
                                    status = reservation.getStatus().trim().toLowerCase();
                                }
                            }
                            reservation.setStatus(status);
                            return reservationDAO.createReservation(reservation);
                        }
                    }
                }
            }
        return null;
    }

    @Override
    @Transactional
    public Reservation findReservationById(int id) {
        return reservationDAO.findReservationById(id);
    }

    @Override
    @Transactional
    public String deleteReservationByID(int id) {
        Reservation reservation = findReservationById(id);
        if (reservation != null) {
            reservationDAO.deleteReservation(id);
            return "Deleted reservation id "+ id;
        }
        return "Reservation id not found";
    }

    @Override
    @Transactional
    public Reservation updateReservation(Reservation reservation)  {
        Reservation currentReservation = findReservationById(reservation.getId());
        if (currentReservation != null) {
            String name = reservation.getName();
            if (name != null && !name.equals("")) {
                currentReservation.setName(name);
            }
            String phone = reservation.getPhone();
            if (phone != null && !phone.equals("")) {
                currentReservation.setPhone(phone);
            }
            String email = reservation.getEmail();
            if (email != null) {
                currentReservation.setEmail(email);
            }
            String note = reservation.getNote();
            if (note != null) {
                currentReservation.setNote(note);
            }
            int tableId = reservation.getTableId();
            if (tableId != 0) {
                currentReservation.setTableId(tableId);
            }
            String status = reservation.getStatus();
            if (status != null) {
                if (Arrays.asList("booked", "cancelled", "done").contains(status.trim().toLowerCase())) {
                    currentReservation.setStatus(status.trim().toLowerCase());
                }
            }
            Timestamp startTime = reservation.getStartTime();
            if (startTime != null) {
                currentReservation.setStartTime(new Timestamp(startTime.getTime() - 1000*60*60*7));
            }
            return reservationDAO.createReservation(currentReservation);
        }
        return null;
    }
}
