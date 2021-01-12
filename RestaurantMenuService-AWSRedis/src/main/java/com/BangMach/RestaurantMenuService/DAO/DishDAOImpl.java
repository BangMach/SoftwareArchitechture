package com.BangMach.RestaurantMenuService.DAO;

import com.BangMach.RestaurantMenuService.model.Dish;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Qualifier("dishDAOImpl")
public class DishDAOImpl implements  DishDAOInterface {

    private final EntityManager entityManager;

    @Autowired
    public DishDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    private Query createQuery(String stringQuery) {
        return (Query) entityManager.createQuery(stringQuery);
    }

    @Override
    public List<Dish> getAllDishes(int startAt, int maxResults) {
        Query query = createQuery("FROM Dish ORDER BY id")
                        .setFirstResult(startAt)
                        .setMaxResults(maxResults);
        return query.getResultList();
    }

    @Override
    public Dish findDishById(int id) {
        return entityManager.find(Dish.class, id);
    }

    @Override
    public Dish saveDish(Dish dish) {
        return entityManager.merge(dish);
    }

    @Override
    public void deleteDishById(int id) {
        Query query = createQuery("delete from Dish where id=:id")
                        .setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Dish> findDishByCategory(String category, int startAt, int maxResults) {
        Query query = createQuery("FROM Dish WHERE category LIKE :category ORDER BY id")
        .setParameter("category", category)
        .setFirstResult(startAt)
        .setMaxResults(maxResults);
        return query.getResultList();
    }

    @Override
    public List<Dish> findDishes(Dish dish, int startAt, int maxResults) {
        String queryString = " FROM Dish WHERE ";
        queryString += (dish.getId() != 0)
                ? " id = :id AND  "
                : " :id = :id AND ";
        queryString += (dish.getCategory() != null)
                ? " category LIKE CASE WHEN :category = '' THEN category ELSE :category END AND  "
                : " :category LIKE :category AND ";
        queryString += (dish.getName() != null)
                ? " name LIKE CASE WHEN :name = '' THEN name ELSE :name END AND  "
                : " :name LIKE :name AND ";
        queryString += (dish.getDescription() != null)
                ? " description LIKE CASE WHEN :description = '' THEN description ELSE :description END  "
                : " :description LIKE :description ";
        queryString += " ORDER BY id";
        Query query = createQuery(queryString)
                        .setParameter("id", dish.getId())
                        .setParameter("category","%" + dish.getCategory() + "%")
                        .setParameter("name","%" + dish.getName() + "%")
                        .setParameter("description", "%" + dish.getDescription() + "%")
                        .setFirstResult(startAt)
                        .setMaxResults(maxResults);
        return query.getResultList();
    }
}
