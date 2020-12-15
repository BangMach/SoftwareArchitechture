package com.Restaurant.RestaurantAccountService.controller;

import com.Restaurant.RestaurantAccountService.model.Account;
import com.Restaurant.RestaurantAccountService.service.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping(path = "/accounts/")
public class AccountController {

    private AccountServiceInterface accountService;

    @Autowired
    public AccountController(AccountServiceInterface accountService){
        this.accountService = accountService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Account addAccount(@RequestBody Account account) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return (accountService.createAccount(account));
    }

    @RequestMapping(value = "/verify/password", method = RequestMethod.POST)
    public Account verifyAccountPassword(@RequestBody Account account) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return accountService.verifyAccountPassword(account);
    }

    @RequestMapping(value = "/verify/email", method = RequestMethod.POST)
    public Account verifyAccountEmail(@RequestParam String email) {
        return accountService.verifyAccountEmail(email);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Account> findAll() {
        return accountService.findAllAccounts();
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public List<Account> findAccounts(@RequestBody Account account) {
        return accountService.findAccounts(account);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Account updateAccount(@RequestBody Account account) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return accountService.updateAccount(account);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String deleteAccount(@RequestParam Integer id){
        return accountService.deleteAccountById(id);
    }
}
