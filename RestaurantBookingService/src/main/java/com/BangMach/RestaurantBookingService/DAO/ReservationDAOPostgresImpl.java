package com.BangMach.RestaurantBookingService.DAO;

import com.BangMach.RestaurantBookingService.Entity.Reservation;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Repository
@Qualifier("PostgresReservationDAOImpl")
public class ReservationDAOPostgresImpl implements ReservationDAO {
    private EntityManager entityManager;
    @Autowired
    public ReservationDAOPostgresImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private Query createQuery(String stringQuery) {
        return (Query) entityManager.createQuery(stringQuery);
    }

    @Override
    @Transactional
    public Collection<Reservation> getAllReservation() {
        Query query = createQuery("from Reservation order by id");
        return query.getResultList();
    }

    @Override
    @Transactional
    public Reservation findReservationById(int id) {
        return entityManager.find(Reservation.class, id);
    }



    @Override
    @Transactional
    public List<Reservation> findReservationByName(String name) {
        Query query = createQuery("from Reservation where name LIKE :reservation");
        query.setParameter("name", name);
        return query.getResultList();    }

    @Override
    @Transactional
    public List<Reservation> findReservationByPhone(String phone) {
        Query query = createQuery("from Reservation where phone LIKE :phone");
        query.setParameter("phone", phone);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Reservation> findReservationByDate(Date date) {
        Query query = createQuery("from Reservation where Date LIKE :Date");
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void insertReservation(Reservation reservation) {
        entityManager.merge(reservation);
    }

    @Override
    @Transactional
    public void deleteReservation(int id) {
        Query query = createQuery("delete from Reservation where id=:id ");
        query.setParameter("id", id );
        query.executeUpdate();
    }
}
