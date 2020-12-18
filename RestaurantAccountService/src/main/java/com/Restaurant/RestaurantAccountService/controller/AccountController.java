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

    @PostMapping("/create")
    public Account addAccount(@RequestBody Account account) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return (accountService.createAccount(account));
    }

    @GetMapping("/find/username")
    public Account findAccountByUsername(@RequestParam String username) {
        return accountService.findAccountByUsername(username);
    }

    @GetMapping("/find/email")
    public Account findAccountByEmail(@RequestParam String email) {
        return accountService.findAccountByEmail(email);
    }

    @GetMapping("/all")
    public List<Account> findAll() {
        return accountService.findAllAccounts();
    }

    @GetMapping("/find")
    public List<Account> findAccounts(@RequestBody Account account) {
        return accountService.findAccounts(account);
    }

    @PutMapping("/update")
    public Account updateAccount(@RequestBody Account account) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return accountService.updateAccount(account);
    }

    @DeleteMapping("/delete")
    public String deleteAccount(@RequestParam Integer id){
        return accountService.deleteAccountById(id);
    }

}
