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

    @RequestMapping(value = "/find/username", method = RequestMethod.GET)
    public Account findAccountByUsername(@RequestParam String username) {
        return accountService.findAccountByUsername(username);
    }

    @RequestMapping(value = "/find/email", method = RequestMethod.GET)
    public Account findAccountByEmail(@RequestParam String email) {
        return accountService.findAccountByEmail(email);
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
