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
            ")"
        );
        query.setParameter("prevTableStartTime", prevTableStartTime);
        query.setParameter("expectedEndTime", expectedEndTime);
        return query.getResultList();
    }

    public List<ReservationDetail> getAll() {
        Query query = createQuery(
    "SELECT new ReservationDetail(" +
            "res.id, res.email, res.name, res.phone, res.startTime, " +
            "res.note, res.status, res.tableId, table.seats) " +
            "FROM RestaurantTable table, Reservation res " +
            "WHERE table.id = res.tableId"
        );
        return query.getResultList();
    }

}
