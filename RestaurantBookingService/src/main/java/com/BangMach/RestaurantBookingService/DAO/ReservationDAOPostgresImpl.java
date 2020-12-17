package com.BangMach.RestaurantBookingService.DAO;

import com.BangMach.RestaurantBookingService.Entity.Reservation;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Repository
@Qualifier("PostgresReservationDAOImpl")
public class ReservationDAOPostgresImpl implements ReservationDAO {
    private EntityManager entityManager;

    private Query createQuery(String stringQuery) {
        return (Query) entityManager.createQuery(stringQuery);
    }


    @Override
    public Collection<Reservation> getAllReservation() {
        Query query = createQuery("from Reservation order by id");
        return query.getResultList();    }

    @Override
    public Reservation getReservationById(int id) {
        return entityManager.find(Reservation.class, id);
    }

    @Override
    public void updateReservation(Reservation reservation) {

    }

    @Override
    public List<Reservation> findReservationByName(String name) {
        Query query = createQuery("from Reservation where name LIKE :reservation");
        query.setParameter("username", name);
        return query.getResultList();    }

    @Override
    public List<Reservation> findReservationByPhone(String phone) {
        Query query = createQuery("from Reservation where phone LIKE :phone");
        query.setParameter("phone", phone);
        return query.getResultList();
    }

    @Override
    public List<Reservation> findReservationByDate(Date date) {
        Query query = createQuery("from Reservation where Date LIKE :Date");
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public void insertReservation(Reservation reservation) {
        entityManager.merge(reservation);
    }

    @Override
    public void deleteReservation(int id) {
        Query query = createQuery("from Reservation delete reservation where id LIKE :=id ");
        query.setParameter("id", id );
        query.executeUpdate();
    }
}
