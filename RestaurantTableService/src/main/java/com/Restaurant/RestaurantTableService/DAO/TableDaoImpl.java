package com.Restaurant.RestaurantTableService.DAO;

import com.Restaurant.RestaurantTableService.model.RestaurantTable;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Qualifier("tableDAOImpl")
public class TableDaoImpl implements TableDAOInterface {

    private final EntityManager entityManager;

    @Autowired
    public TableDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    private Query createQuery(String stringQuery) {
        return (Query) entityManager.createQuery(stringQuery);
    }

    @Override
    public List<RestaurantTable> getAllTables(){
        Query query = createQuery("from RestaurantTable order by id");
        return query.getResultList();
    }

    @Override
    public RestaurantTable findTableById(int id) {
        return entityManager.find(RestaurantTable.class, id);
    }

    @Override
    public List<RestaurantTable> findTables(RestaurantTable table) {
        if (table.getStatus() == null) {
            table.setStatus("");
        }
        Query query = createQuery(
        "from RestaurantTable where " +
                "seats = CASE WHEN :seats = 0 THEN seats ELSE :seats END " +
                "AND status = CASE WHEN :status = '' THEN status ELSE :status END "
        );
        query.setParameter("seats", table.getSeats());
        query.setParameter("status", table.getStatus());
        return query.getResultList();
    }

    @Override
    public RestaurantTable saveTable(RestaurantTable table) {
        return entityManager.merge(table);
    }

}
