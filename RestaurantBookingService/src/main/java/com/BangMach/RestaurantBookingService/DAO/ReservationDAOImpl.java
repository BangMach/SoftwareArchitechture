package com.BangMach.RestaurantBookingService.DAO;

import com.BangMach.RestaurantBookingService.Entity.Reservation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Reservation> getAllReservation() {
    }

    @Override
    public Reservation getReservationById(int id) {
        return reservations.get(id);
    }

    @Override
    public void updateReservation(Reservation reservation) {
        int id = reservation.getId()
        return reservations.put(id, reservation);

    }


    @Override
    public void insertReservation(Reservation reservation) {
    }
}
