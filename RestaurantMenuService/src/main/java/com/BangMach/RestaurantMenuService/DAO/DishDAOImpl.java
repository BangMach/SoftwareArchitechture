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
    public List<Dish> getAllDishes() {
        Query query = createQuery("from Dish order by id");
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
        Query query = createQuery("delete from Dish where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Dish> findDishByCategory(String category) {
        Query query = createQuery(
    "from Dish where " +
            "category LIKE :category "
        );
        query.setParameter("category", category);
        return query.getResultList();
    }
}
