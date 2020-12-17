package com.Restaurant.RestaurantAccountService.service;

import com.Restaurant.RestaurantAccountService.model.Account;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface AccountServiceInterface {

    Account createAccount(Account account) throws NoSuchAlgorithmException, InvalidKeySpecException;

    Account findAccountByUsername(String username);

    Account findAccountByEmail(String email);

    List<Account> findAllAccounts();

    Account findAccountById(int id);

    List<Account> findAccounts(Account account);

    Account updateAccount(Account account) throws InvalidKeySpecException, NoSuchAlgorithmException;

    String deleteAccountById(int id);
}
