package com.Restaurant.RestaurantAccountService.controller;

import com.Restaurant.RestaurantAccountService.model.Account;
import com.Restaurant.RestaurantAccountService.service.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(path = "/accounts")
public class AccountController {

    private final AccountServiceInterface accountService;

    @Autowired
    public AccountController(AccountServiceInterface accountService){
        this.accountService = accountService;
    }

    @PostMapping(value = "")
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
                HttpStatus.OK
            );
        }
    }

    @GetMapping(value = "")
    public List<Account> findAll(@RequestParam(value= "startAt", defaultValue = "0") Integer startAt, @RequestParam(value= "maxResults", defaultValue = "50") Integer maxResults) {
        return accountService.getAllAccounts(startAt, maxResults);
    }

    @PutMapping(value = "")
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
                    HttpStatus.OK
            );
        }
    }

    @DeleteMapping(value = "/{id}")
    public String deleteAccount(@PathVariable Integer id){
        return accountService.deleteAccountById(id);
    }

    @PostMapping(value = "/attributes")
    public List<Account> findAccounts(@RequestBody Account account, @RequestParam(value= "startAt", defaultValue = "0") Integer startAt, @RequestParam(value= "maxResults", defaultValue = "50") Integer maxResults) {
        return accountService.findAccounts(account, startAt, maxResults);
    }

    @GetMapping(value = "/email/{email}")
    public Account findAccountByEmail(@PathVariable String email) {
        return accountService.findAccountByEmail(email);
    }

    @PostMapping("/password")
    public Timestamp verifyAccountPassword(@RequestBody Account account) throws IOException {
        Account verifiedAccount = accountService.verifyAccountPassword(account);
        if (verifiedAccount == null) {
            return null;
        } else {
            return new Timestamp(System.currentTimeMillis());
        }
    }

}
