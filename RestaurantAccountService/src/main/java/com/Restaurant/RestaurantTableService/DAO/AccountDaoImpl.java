package com.Restaurant.RestaurantTableService.DAO;

import com.Restaurant.RestaurantTableService.model.Account;
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
    public List<Account> getAllAccounts(int startAt, int maxResults){
        Query query = createQuery("FROM Account ORDER BY id")
                        .setFirstResult(startAt)
                        .setMaxResults(maxResults);
        return query.getResultList();
    }

    @Override
    public Account findAccountById(int id) {
        return entityManager.find(Account.class, id);
    }

    @Override
    public List<Account> findAccountByUsername(String username) {
        Query query = createQuery("from Account where username LIKE :username")
                        .setParameter("username", username);
        return query.getResultList();
    }
    @Override

    public List<Account> findAccountByEmail(String email) {
        Query query = createQuery("from Account where email LIKE :email")
                        .setParameter("email", email);
        return query.getResultList();
    }

    @Override
    public List<Account> findAccounts(Account account, int startAt, int maxResults) {
        String queryString = " FROM Account WHERE ";
        queryString += (account.getId() != 0)
                ? " id = :id AND  "
                : " :id = :id AND ";
        queryString += (account.getUsername() != null)
                ? " username LIKE CASE WHEN :username = '' THEN username ELSE :username END AND  "
                : " :username LIKE :username AND ";
        queryString += (account.getEmail() != null)
                ? " email LIKE CASE WHEN :email = '' THEN email ELSE :email END AND  "
                : " :email LIKE :email AND ";
        queryString += (account.getFullName() != null)
                ? " fullName LIKE CASE WHEN :fullName = '' THEN fullName ELSE :fullName END AND  "
                : " :fullName LIKE :fullName AND ";
        queryString += (account.getAddress() != null)
                ? " address LIKE CASE WHEN :address = '' THEN address ELSE :address END AND  "
                : " :address LIKE :address AND ";
        queryString += (account.getPhone() != null)
                ? " phone LIKE CASE WHEN :phone = '' THEN phone ELSE :phone END  "
                : " :phone LIKE :phone ";
        queryString += " ORDER BY id";
        Query query = createQuery(queryString)
                .setParameter("id", account.getId())
                .setParameter("username","%" + account.getUsername() + "%")
                .setParameter("fullName","%" + account.getFullName() + "%")
                .setParameter("phone", "%" + account.getPhone() + "%")
                .setParameter("address", "%" + account.getAddress() + "%")
                .setParameter("email", "%" + account.getEmail() + "%")
                .setFirstResult(startAt)
                .setMaxResults(maxResults);
        return query.getResultList();
    }

    @Override
    public Account saveAccount(Account account) {
        return entityManager.merge(account);
    }

    @Override
    public void deleteAccountById(int id) {
        Query query = createQuery("delete from Account where id=:id").setParameter("id", id);
        query.executeUpdate();
    }
}
