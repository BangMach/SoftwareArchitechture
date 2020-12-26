package com.BangMach.RestaurantUserService.DAO;

import com.BangMach.RestaurantUserService.model.ReservationDetail;
import com.BangMach.RestaurantUserService.model.RestaurantTable;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.List;

@Repository
@Qualifier("userDAOImpl")
public class UserDAOImpl implements UserDAOInterface {

    private final EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    private Query createQuery(String stringQuery) {
        return (Query) entityManager.createQuery(stringQuery);
    }

    @Override
    public List<RestaurantTable> searchAvailableTables(Timestamp startTime) {
        Timestamp prevTableStartTime = new Timestamp(startTime.getTime() - (1000 * 60 * 60 * 2));
        Timestamp expectedEndTime = new Timestamp(startTime.getTime() + (1000 * 60 * 60 * 2));
        Query query = createQuery(
    "SELECT table " +
            "FROM RestaurantTable table " +
            "WHERE table.id " +
            "NOT IN ( " +
                "SELECT resv.tableId " +
                "FROM Reservation resv " +
                "WHERE resv.startTime >= :prevTableStartTime " +
                "AND resv.startTime <= :expectedEndTime " +
                "AND resv.status = 'booked' " +
            ") " +
            "AND table.status = 'available' " +
            "ORDER BY id"
        )
        .setParameter("prevTableStartTime", prevTableStartTime)
        .setParameter("expectedEndTime", expectedEndTime);
        return query.getResultList();
    }

    @Override
    public List<ReservationDetail> findReservationDetails(ReservationDetail reservationDetail, int startAt, int maxResults) {
        String queryString = "SELECT new ReservationDetail( " +
            "res.id, res.email, res.name, res.phone, res.startTime, " +
            "res.note, res.status, res.tableId, table.seats) " +
            "FROM RestaurantTable table, Reservation res " +
            "WHERE table.id = res.tableId AND ";
        queryString += (reservationDetail.getId() != 0)
                ? " res.id = :id AND  "
                : " :id = :id AND ";
        queryString += (reservationDetail.getName() != null)
                ? " res.name LIKE CASE WHEN :name = '' THEN res.name ELSE :name END  AND "
                : " :name LIKE :name AND";
        queryString += (reservationDetail.getEmail() != null)
                ? " res.email LIKE CASE WHEN :email = '' THEN res.email ELSE :email END AND "
                : " :email LIKE :email AND ";
        queryString += (reservationDetail.getPhone() != null)
                ? " res.phone LIKE CASE WHEN :phone = '' THEN res.phone ELSE :phone END AND "
                : " :phone LIKE :phone AND ";
        queryString += (reservationDetail.getTableId() != 0)
                ? " res.tableId = :tableId AND "
                : " :tableId = :tableId AND ";
        queryString += (reservationDetail.getSeats() != 0)
                ? " table.seats = :seats AND "
                : " :seats = :seats AND ";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (reservationDetail.getStartTime() != null) {
            queryString += " res.startTime = :startTime AND ";
            timestamp = new Timestamp(reservationDetail.getStartTime().getTime() - (1000 *60*60*7));
        } else {
            queryString += " :startTime = :startTime AND ";
        }
        queryString += (reservationDetail.getNote() != null)
                ? " res.note LIKE CASE WHEN :note = '' THEN res.note ELSE :note END AND  "
                : " :note LIKE :note AND ";
        queryString += (reservationDetail.getStatus() != null)
                ? " res.status LIKE CASE WHEN :status = '' THEN res.status ELSE :status END  "
                : " :status LIKE :status ";
        queryString += " ORDER BY res.id";
        Query query = createQuery(queryString)
                        .setParameter("id", reservationDetail.getId())
                        .setParameter("name", "%" + reservationDetail.getName() + "%")
                        .setParameter("email", "%" + reservationDetail.getEmail() + "%")
                        .setParameter("phone", "%" + reservationDetail.getPhone() + "%")
                        .setParameter("tableId",  reservationDetail.getTableId())
                        .setParameter("seats", reservationDetail.getSeats())
                        .setParameter("startTime", timestamp)
                        .setParameter("note", "%" + reservationDetail.getNote() + "%")
                        .setParameter("status", "%" + reservationDetail.getStatus() + "%")
                        .setFirstResult(startAt)
                        .setMaxResults(maxResults);
        return query.getResultList();
    }

    @Override
    public List<ReservationDetail> getAllReservationDetails(int startAt, int maxResults) {
        String queryString = "SELECT new ReservationDetail( " +
                "res.id, res.email, res.name, res.phone, res.startTime, " +
                "res.note, res.status, res.tableId, table.seats) " +
                "FROM RestaurantTable table, Reservation res " +
                "WHERE table.id = res.tableId " +
                "ORDER BY res.id";
        Query query = createQuery(queryString)
                        .setFirstResult(startAt)
                        .setMaxResults(maxResults);
        return query.getResultList();
    }

}
