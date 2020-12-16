package com.Restaurant.RestaurantLoginService.DAO;

import com.Restaurant.RestaurantLoginService.model.Account;
import java.util.List;

public interface AccountDAOInterface {

    List<Account> findAccountByUsername(String username);

    List<Account> findAccountByEmail(String email);

}
