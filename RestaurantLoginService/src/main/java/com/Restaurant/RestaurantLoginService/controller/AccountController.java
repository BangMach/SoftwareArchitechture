package com.Restaurant.RestaurantLoginService.controller;

import com.Restaurant.RestaurantLoginService.model.Account;
import com.Restaurant.RestaurantLoginService.service.LoginServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping(path = "/accounts/")
public class AccountController {

    private LoginServiceInterface accountService;

    @Autowired
    public AccountController(LoginServiceInterface accountService){
        this.accountService = accountService;
    }

    @RequestMapping(value = "/verify/password", method = RequestMethod.POST)
    public Account verifyAccountPassword(@RequestBody Account account) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return accountService.verifyAccountPassword(account);
    }

    @RequestMapping(value = "/verify/email", method = RequestMethod.POST)
    public Account verifyAccountEmail(@RequestParam String email) {
        return accountService.verifyAccountEmail(email);
    }

}
