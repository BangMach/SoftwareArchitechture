package com.Restaurant.RestaurantLoginService.DAO;

import com.Restaurant.RestaurantLoginService.model.Account;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Qualifier("accountDAOImpl")
public class AccountDaoImpl implements AccountDAOInterface {

    private EntityManager entityManager;

    @Autowired
    public AccountDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    private Query createQuery(String stringQuery) {
        return (Query) entityManager.createQuery(stringQuery);
    }

    @Override
    public List<Account> findAccountByUsername(String username) {
        Query query = createQuery("from Account where username LIKE :username");
        query.setParameter("username", username);
        return query.getResultList();
    }
    @Override

    public List<Account> findAccountByEmail(String email) {
        Query query = createQuery("from Account where email LIKE :email");
        query.setParameter("email", email);
        return query.getResultList();
    }

}
