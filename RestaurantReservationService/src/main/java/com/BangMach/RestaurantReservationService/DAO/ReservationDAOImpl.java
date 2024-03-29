package com.BangMach.RestaurantReservationService.DAO;

import com.BangMach.RestaurantReservationService.Entity.Reservation;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Qualifier("ReservationDAOImpl")
public class ReservationDAOImpl implements ReservationDAO {

    private final EntityManager entityManager;

    @Autowired
    public ReservationDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private Query createQuery(String stringQuery) {
        return (Query) entityManager.createQuery(stringQuery);
    }

    @Override
    @Transactional
    public Reservation findReservationById(int id) {
        return entityManager.find(Reservation.class, id);
    }

    @Override
    @Transactional
    public Reservation createReservation(Reservation reservation) {
        return entityManager.merge(reservation);
    }

    @Override
    @Transactional
    public void deleteReservation(int id) {
        Query query = createQuery("DELETE FROM Reservation WHERE id=:id ");
        query.setParameter("id", id );
        query.executeUpdate();
    }
}
