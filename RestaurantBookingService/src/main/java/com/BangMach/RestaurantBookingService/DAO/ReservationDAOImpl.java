package com.BangMach.RestaurantBookingService.DAO;

import com.BangMach.RestaurantBookingService.Entity.Reservation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Qualifier("HardCodeImpl")
public class ReservationDAOImpl implements ReservationDAO {
    static Map<Integer,Reservation> reservations;
    static {
        ReservationDAOImpl.reservations = new HashMap<Integer, Reservation>() {
            {
                put(1, new Reservation(1, "A1", "Bang Mach", "+84906973206", new Date()));
                put(1, new Reservation(1, "A1", "Bang Mach", "+84906973206", new Date()));
                put(1, new Reservation(1, "A1", "Bang Mach", "+84906973206", new Date()));
                put(1, new Reservation(1, "A1", "Bang Mach", "+84906973206", new Date()));
                put(1, new Reservation(1, "A1", "Bang Mach", "+84906973206", new Date()));
                put(1, new Reservation(1, "A1", "Bang Mach", "+84906973206", new Date()));
                put(1, new Reservation(1, "A1", "Bang Mach", "+84906973206", new Date()));

            }
            private int id;
            private String name;
            private String phone;
            private Integer numberOfPeople;
            private Date startTime;
            private String roomCode;

        };

    }
    @Override
    public void deleteReservation(int id) {
    }

    @Override
    public Collection<Reservation> getAllReservation() {
        return reservations.values();
    }

    @Override
    public Reservation getReservationById(int id) {
        return reservations.get(id);
    }

    @Override
    public void updateReservation(Reservation reservation) {
        int id = reservation.getId();
        reservations.put(id, reservation);

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


    @Override
    public void insertReservation(Reservation reservation) {
    }
}
