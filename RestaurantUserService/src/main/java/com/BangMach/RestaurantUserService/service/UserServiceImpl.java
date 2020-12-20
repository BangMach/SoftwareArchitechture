package com.BangMach.RestaurantUserService.service;

import com.BangMach.RestaurantUserService.DAO.UserDAOInterface;
import com.BangMach.RestaurantUserService.model.Reservation;
import com.BangMach.RestaurantUserService.model.ReservationDetail;
import com.BangMach.RestaurantUserService.model.RestaurantTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Service
public class UserServiceImpl implements UserServiceInterface {

    private final UserDAOInterface userDAO;
    public RestTemplate restTemplate;

    @Autowired
    public UserServiceImpl(@Qualifier("userDAOImpl") UserDAOInterface userDAO, RestTemplate restTemplate){
        this.userDAO = userDAO;
        this.restTemplate = restTemplate;
    }

    @Override
    @Transactional
    public List<RestaurantTable> searchAvailableTables(Timestamp startTime) {
        return userDAO.searchAvailableTables(startTime);
    }

    @Override
    @Transactional
    public List<ReservationDetail> getAll() {
        return userDAO.getAll();
    }

    @Override
    @Transactional
    public Reservation createReservation(Reservation reservation) {
        if (checkAvailableTableForCreate(reservation.getTableId(), reservation.getStartTime())) {
            String url = "http://RESERVATION-SERVICE/reservations/create";
            return restTemplate.postForObject(url, reservation, Reservation.class);
        }
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<Reservation> updateReservation(Reservation reservation) {
       if (checkAvailableTableForUpdate(reservation.getTableId(), reservation.getStartTime())) {
            String url = "http://RESERVATION-SERVICE/reservations/update";
            HttpEntity<Reservation> requestEntity = new HttpEntity<>(reservation);
            return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Reservation.class);
        }
        return null;
    }

    private boolean checkAvailableTableForCreate(int tableId, Timestamp timestamp) {
        String url = "http://TABLE-SERVICE/tables/find/id?id=" + tableId;
        RestaurantTable reservedTable = restTemplate.getForObject(url, RestaurantTable.class);
        if (timestamp != null && reservedTable != null) {
            List<RestaurantTable> reservableTables = searchAvailableTables(new Timestamp(timestamp.getTime() - 1000*60*60*7));
            return reservableTables.stream().anyMatch(o -> o.getId() == reservedTable.getId());
        }
        return false;
    }

    private boolean checkAvailableTableForUpdate(int tableId, Timestamp timestamp) {
        String url = "http://TABLE-SERVICE/tables/find/id?id=" + tableId;
        RestaurantTable reservedTable = restTemplate.getForObject(url, RestaurantTable.class);
        if (tableId == 0) {
            return (timestamp == null);
        } else if (reservedTable != null) {
            if (timestamp != null) {
                List<RestaurantTable> reservableTables = searchAvailableTables(new Timestamp(timestamp.getTime() - 1000*60*60*7));
                return reservableTables.stream().anyMatch(o -> o.getId() == reservedTable.getId());
            } else {
                return false;
            }
        }
        return false;
    }

}
