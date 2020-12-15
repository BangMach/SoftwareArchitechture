package com.Restaurant.RestaurantAccountService.controller;

import com.Restaurant.RestaurantAccountService.model.Account;
import com.Restaurant.RestaurantAccountService.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/accounts/")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(path = "/read/all", method = RequestMethod.GET)
    public List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }
}
