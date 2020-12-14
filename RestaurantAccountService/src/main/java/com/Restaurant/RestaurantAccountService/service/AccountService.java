package com.Restaurant.RestaurantAccountService.service;

import com.Restaurant.RestaurantAccountService.model.Account;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Service
public class AccountService {

    @PersistenceContext
    private EntityManager entityManager;

    private Query createQuery(String stringQuery) {
        return entityManager.createQuery(stringQuery);
    }

    public List<Account> getAllAccounts(){
        Query query = createQuery("from Account order by id");
        return query.getResultList();

    }
}
