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

    private EntityManager entityManager;

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
        Query query = createQuery(
        "from RestaurantTable where " +
                "seats LIKE CASE WHEN :seats = 0 OR :seats IS NULL THEN seats ELSE :seats END"
        );
        query.setParameter("seats", "%" + table.getSeats() + "%");
        return query.getResultList();
    }

    @Override
    public RestaurantTable saveTable(RestaurantTable table) {
        return entityManager.merge(table);
    }

    @Override
    public void deleteTableById(int id) {
        Query query = createQuery("delete from RestaurantTable where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
