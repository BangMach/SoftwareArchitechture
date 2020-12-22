package com.Restaurant.RestaurantAccountService.DAO;

import com.Restaurant.RestaurantAccountService.model.Account;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Qualifier("accountDAOImpl")
public class AccountDaoImpl implements AccountDAOInterface {

    private final EntityManager entityManager;

    @Autowired
    public AccountDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    private Query createQuery(String stringQuery) {
        return (Query) entityManager.createQuery(stringQuery);
    }

    @Override
    public List<Account> getAllAccounts(){
        Query query = createQuery("from Account order by id");
        return query.getResultList();
    }

    @Override
    public Account findAccountById(int id) {
        return entityManager.find(Account.class, id);
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

    @Override
    public List<Account> findAccounts(Account account) {
        if (account.getUsername() == null) {
            account.setUsername("");
        }
        if (account.getEmail() == null) {
            account.setEmail("");
        }
        if (account.getFullName() == null) {
            account.setFullName("");
        }
        if (account.getAddress() == null) {
            account.setAddress("");
        }
        if (account.getPhone() == null) {
            account.setPhone("");
        }
        Query query = createQuery(
                " from Account where " +
                "username LIKE CASE WHEN :username = '' THEN username ELSE :username END AND  " +
                "fullName LIKE CASE WHEN :fullName = '' THEN fullName ELSE :fullName END AND  " +
                "phone LIKE CASE WHEN :phone = '' THEN phone ELSE :phone END AND " +
                "address LIKE CASE WHEN :address = '' THEN address ELSE :address END AND " +
                "email LIKE CASE WHEN :email = '' THEN email ELSE :email END "
        );
        query.setParameter("username","%" + account.getUsername() + "%");
        query.setParameter("fullName","%" + account.getFullName() + "%");
        query.setParameter("phone", "%" + account.getPhone() + "%");
        query.setParameter("address", "%" + account.getAddress() + "%");
        query.setParameter("email", "%" + account.getEmail() + "%");
        return query.getResultList();
    }

    @Override
    public Account saveAccount(Account account) {
        return entityManager.merge(account);
    }

    @Override
    public void deleteAccountById(int id) {
        Query query = createQuery("delete from Account where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
