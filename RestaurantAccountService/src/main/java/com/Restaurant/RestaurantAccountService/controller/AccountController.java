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

    private final AccountServiceInterface accountService;

    @Autowired
    public AccountController(AccountServiceInterface accountService){
        this.accountService = accountService;
    }

    @PostMapping(value = "/create")
    public Account addAccount(@RequestBody Account account) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return (accountService.createAccount(account));
    }

    @GetMapping(value = "/find/username")
    public Account findAccountByUsername(@RequestParam String username) {
        return accountService.findAccountByUsername(username);
    }

    @GetMapping(value = "/find/email")
    public Account findAccountByEmail(@RequestParam String email) {
        return accountService.findAccountByEmail(email);
    }

    @GetMapping(value = "/all")
    public List<Account> findAll() {
        return accountService.getAllAccounts();
    }

    @GetMapping(value = "/filter")
    public List<Account> findAccounts(@RequestBody Account account) {
        return accountService.findAccounts(account);
    }

    @PutMapping(value = "/update")
    public Account updateAccount(@RequestBody Account account) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return accountService.updateAccount(account);
    }

    @PostMapping(value = "/delete")
    public String deleteAccount(@RequestParam Integer id){
        return accountService.deleteAccountById(id);
    }

}
