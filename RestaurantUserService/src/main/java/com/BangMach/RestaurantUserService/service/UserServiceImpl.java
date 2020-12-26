package com.BangMach.RestaurantUserService.service;

import com.BangMach.RestaurantUserService.DAO.UserDAOInterface;
import com.BangMach.RestaurantUserService.model.Reservation;
import com.BangMach.RestaurantUserService.model.ReservationDetail;
import com.BangMach.RestaurantUserService.model.RestaurantTable;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
    public UserServiceImpl(@Qualifier("userDAOImpl") UserDAOInterface userDAO, RestTemplate restTemplate) {
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
    public List<ReservationDetail> findReservationDetails(ReservationDetail reservationDetail, int startAt, int maxResults) {
        return userDAO.findReservationDetails(reservationDetail, startAt, maxResults);
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
        if (checkAvailableTableForUpdate(reservation.getTableId(), reservation.getStartTime(), reservation.getId())) {
            String url = "http://RESERVATION-SERVICE/reservations/update";
            HttpEntity<Reservation> requestEntity = new HttpEntity<>(reservation);
            return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Reservation.class);
        }
        return null;
    }

    private boolean checkAvailableTableForCreate(int tableId, Timestamp timestamp) {
        String url = "http://TABLE-SERVICE/tables/find?id=" + tableId;
        RestaurantTable reservedTable = getRestaurantTable(url);
        if (timestamp != null && reservedTable != null) {
            List<RestaurantTable> reservableTables = searchAvailableTables(
                    new Timestamp(timestamp.getTime() - 1000 * 60 * 60 * 7)
            );
            return reservableTables.stream().anyMatch(o -> o.getId() == reservedTable.getId());
        }
        return false;
    }

    @HystrixCommand(fallbackMethod = "getFallbackTables")
    private RestaurantTable getRestaurantTable(String url) {
        return restTemplate.getForObject(url, RestaurantTable.class);
    }

    private RestaurantTable getFallbackTables() {
        return new RestaurantTable(1, 2, "not available");
    }


    private boolean checkAvailableTableForUpdate(int tableId, Timestamp timestamp, int id) {
        String currentReservationURL = "http://RESERVATION-SERVICE/reservations/find?id=" + id;
        Reservation currentReservation = getReservation(currentReservationURL);
        if (currentReservation != null) {
            if (tableId == 0 || tableId == currentReservation.getTableId()) {
                return (timestamp == null) || (new Timestamp(timestamp.getTime() - 1000 * 60 * 60 * 7).equals(currentReservation.getStartTime()));
            } else {
                String changeTableURL = "http://TABLE-SERVICE/tables/find?id=" + tableId;
                RestaurantTable changeTable = getRestaurantTable(changeTableURL);
                if (changeTable != null) {
                    if (timestamp != null) {
                        List<RestaurantTable> reservableTables = searchAvailableTables(
                                new Timestamp(timestamp.getTime() - 1000 * 60 * 60 * 7)
                        );
                        return reservableTables.stream().anyMatch(o -> o.getId() == changeTable.getId());
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    @HystrixCommand(fallbackMethod = "getFallbackReservation",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeinMilliseconds",value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
    })
    private Reservation getReservation(String currentReservationURL) {
        return restTemplate.getForObject(currentReservationURL, Reservation.class);
    }

    private Reservation getFallbackReservation() {
        return new Reservation(0, "machdieubang2110@gmail.com", "user doesn't exist", "00", new Timestamp(System.currentTimeMillis()), "empty reservation", "not available", 1);

    }
}

