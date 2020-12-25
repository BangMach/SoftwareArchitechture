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
                "AND resv.status = 'booked'" +
            ") " +
            "AND table.status = 'available'"
        )
        .setParameter("prevTableStartTime", prevTableStartTime)
        .setParameter("expectedEndTime", expectedEndTime);
        return query.getResultList();
    }

    public List<ReservationDetail> findReservationDetails(ReservationDetail reservationDetail, int startAt, int maxResults) {
       String queryString = "SELECT new ReservationDetail( " +
            "res.id, res.email, res.name, res.phone, res.startTime, " +
            "res.note, res.status, res.tableId, table.seats) " +
            "FROM RestaurantTable table, Reservation res " +
            "WHERE table.id = res.tableId AND ";
        queryString += (reservationDetail.getId() != 0)
                ? " table.id = :id AND  "
                : " :id = :id AND ";
        queryString += (reservationDetail.getName() != null)
                ? " table.name LIKE CASE WHEN :name = '' THEN table.name ELSE :name END AND  "
                : " :name LIKE :name AND ";
        queryString += (reservationDetail.getEmail() != null)
                ? " table.email LIKE CASE WHEN :email = '' THEN table.email ELSE :email END AND  "
                : " :email LIKE :email AND ";
        queryString += (reservationDetail.getPhone() != null)
                ? " table.phone LIKE CASE WHEN :phone = '' THEN table.phone ELSE :phone END  "
                : " :phone LIKE :phone ";
        queryString += (reservationDetail.getTableId() != 0)
                ? " table.tableId = :tableId AND  "
                : " :tableId = :tableId AND ";
        queryString += (reservationDetail.getSeats() != 0)
                ? " table.seats = :seats AND  "
                : " :seats = :seats AND ";
        queryString += (reservationDetail.getStartTime() != null)
                ? " table.startTime LIKE CASE WHEN :startTime = '' THEN table.startTime ELSE :startTime END AND  "
                : " :startTime LIKE :startTime AND ";
        queryString += (reservationDetail.getNote() != null)
                ? " table.note LIKE CASE WHEN :note = '' THEN table.note ELSE :note END AND  "
                : " :note LIKE :note AND ";
        queryString += (reservationDetail.getStatus() != null)
                ? " table.status LIKE CASE WHEN :status = '' THEN table.status ELSE :status END  "
                : " :status LIKE :status ";
        Query query = createQuery(queryString)
                        .setParameter("id", reservationDetail.getId())
                        .setParameter("phone", "%" + reservationDetail.getPhone() + "%")
                        .setParameter("email", "%" + reservationDetail.getEmail() + "%")
                        .setParameter("name", "%" + reservationDetail.getName() + "%")
                        .setParameter("tableId",  reservationDetail.getTableId())
                        .setParameter("seats", reservationDetail.getSeats())
                        .setParameter("startTime", reservationDetail.getStartTime())
                        .setParameter("note", "%" + reservationDetail.getNote() + "%")
                        .setParameter("status", "%" + reservationDetail.getStatus() + "%")
                        .setFirstResult(startAt)
                        .setMaxResults(maxResults);
        return query.getResultList();
    }

}
