package com.Restaurant.RestaurantAccountService.DAO;

import com.Restaurant.RestaurantAccountService.model.RestaurantTable;
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
    public List<RestaurantTable> getAllTables(int startAt, int maxResults){
        Query query = createQuery("FROM RestaurantTable ORDER BY id")
                        .setFirstResult(startAt)
                        .setMaxResults(maxResults);
        return query.getResultList();
    }

    @Override
    public RestaurantTable findTableById(int id) {
        return entityManager.find(RestaurantTable.class, id);
    }

    @Override
    public List<RestaurantTable> findTables(RestaurantTable table, int startAt, int maxResults) {
        String queryString = " FROM RestaurantTable WHERE ";
        queryString += (table.getId() != 0)
                ? " id = :id AND  "
                : " :id = :id AND ";
        queryString += (table.getSeats() != 0)
                ? " seats = :seats AND "
                : " :seats = :seats AND ";
        queryString += (table.getStatus() != null)
                ? " status LIKE CASE WHEN :status = '' THEN status ELSE :status END "
                : " :status LIKE :status ";
        queryString += " ORDER BY id";
        Query query = createQuery(queryString)
                        .setParameter("id", table.getId())
                        .setParameter("seats", table.getSeats())
                        .setParameter("status", "%" + table.getStatus() + "%")
                        .setFirstResult(startAt)
                        .setMaxResults(maxResults);
        return query.getResultList();
    }

    @Override
    public RestaurantTable saveTable(RestaurantTable table) {
        return entityManager.merge(table);
    }

}
