package com.Restaurant.RestaurantAccountService.controller;

import com.Restaurant.RestaurantAccountService.model.Account;
import com.Restaurant.RestaurantAccountService.service.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity addAccount(@RequestBody Account account) throws InvalidKeySpecException, NoSuchAlgorithmException {
        Account newAccount = accountService.createAccount(account);
        if (newAccount == null) {
            return new ResponseEntity<>(
                    "Failed to create new account",
                    HttpStatus.BAD_REQUEST
            );
        } else {
            return new ResponseEntity<>(
                    newAccount,
                    HttpStatus.BAD_REQUEST
            );
        }
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
    public ResponseEntity updateAccount(@RequestBody Account account) throws InvalidKeySpecException, NoSuchAlgorithmException {
        Account updatedAccount = accountService.updateAccount(account);
        if (updatedAccount == null) {
            return new ResponseEntity<>(
                    "Failed to update account",
                    HttpStatus.BAD_REQUEST
            );
        } else {
            return new ResponseEntity<>(
                    updatedAccount,
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping(value = "/delete")
    public String deleteAccount(@RequestParam Integer id){
        return accountService.deleteAccountById(id);
    }

}
