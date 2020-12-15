package com.Restaurant.RestaurantLoginService.service;

import com.Restaurant.RestaurantLoginService.model.Account;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface LoginServiceInterface {

    Account verifyAccountPassword(Account account) throws NoSuchAlgorithmException, InvalidKeySpecException;

    Account verifyAccountEmail(String email);

}
