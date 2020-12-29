package com.Restaurant.RestaurantTableService.DAO;

import com.Restaurant.RestaurantTableService.model.Account;

import java.util.List;

public interface AccountDAOInterface {

    List<Account> getAllAccounts(int startAt, int maxResults);

    Account findAccountById(int id);

    List<Account> findAccountByUsername(String username);

    List<Account> findAccountByEmail(String email);

    List<Account> findAccounts(Account account, int startAt, int maxResults);

    Account saveAccount(Account account);

    void deleteAccountById(int id);
}
