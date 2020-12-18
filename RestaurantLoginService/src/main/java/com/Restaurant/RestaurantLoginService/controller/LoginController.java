package com.Restaurant.RestaurantLoginService.controller;

import com.Restaurant.RestaurantLoginService.model.Account;
import com.Restaurant.RestaurantLoginService.service.LoginServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping(path = "/login/")
public class LoginController {

    private LoginServiceInterface loginService;

    @Autowired
    public LoginController(LoginServiceInterface accountService){
        this.loginService = accountService;
    }

    @RequestMapping(value = "/verify/password", method = RequestMethod.POST)
    public ResponseEntity verifyAccountPassword(@RequestBody Account account) throws IOException {
        Account verifiedAccount = loginService.verifyAccountPassword(account);
        if (verifiedAccount == null) {
            return new ResponseEntity<>(
                "User not found",
                HttpStatus.BAD_REQUEST
            );
        } else {
            return new ResponseEntity<>(
                verifiedAccount,
                HttpStatus.OK
            );
        }
    }

    @RequestMapping(value = "/verify/email", method = RequestMethod.POST)
    public Account verifyAccountEmail(@RequestParam String email) {
        return loginService.verifyAccountEmail(email);
    }

}
