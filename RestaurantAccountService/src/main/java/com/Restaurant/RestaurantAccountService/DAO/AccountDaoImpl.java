package com.Restaurant.RestaurantAccountService.DAO;

import com.Restaurant.RestaurantAccountService.model.Account;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
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
        Query query = createQuery(
        "from Account where " +
                "fullName LIKE CASE WHEN :fullName = '' OR :fullName IS NULL THEN fullName ELSE :fullName END AND  " +
                "phone LIKE CASE WHEN :phone = '' OR :phone IS NULL THEN phone ELSE :phone END AND " +
                "address LIKE CASE WHEN :address = '' OR :address IS NULL THEN address ELSE :address END AND " +
                "email LIKE CASE WHEN :email = '' OR :email IS NULL THEN email ELSE :email END "
        );
        query.setParameter("fullName","%" + account.getFullName() + "%");
        query.setParameter("phone", "%" +account.getPhone() + "%");
        query.setParameter("address", "%" +account.getAddress() + "%");
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
