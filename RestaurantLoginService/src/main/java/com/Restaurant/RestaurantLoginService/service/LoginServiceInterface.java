package com.Restaurant.RestaurantLoginService.service;

import com.Restaurant.RestaurantLoginService.model.Account;
import java.io.IOException;

public interface LoginServiceInterface {

    Account verifyAccountPassword(Account account) throws IOException;

    Account verifyAccountEmail(String email);

}
