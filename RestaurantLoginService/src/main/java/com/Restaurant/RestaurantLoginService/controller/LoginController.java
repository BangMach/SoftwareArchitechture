package com.Restaurant.RestaurantLoginService.controller;

import com.Restaurant.RestaurantLoginService.model.Account;
import com.Restaurant.RestaurantLoginService.service.LoginServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.io.IOException;

@RestController
@RequestMapping(path = "/login/")
public class LoginController {

    private final LoginServiceInterface loginService;

    @Autowired
    public LoginController(LoginServiceInterface accountService){
        this.loginService = accountService;
    }

    @PostMapping("/password")
    public RedirectView verifyAccountPassword(@RequestBody Account account) throws IOException {
        Account verifiedAccount = loginService.verifyAccountPassword(account);
        RedirectView redirectView = new RedirectView();
        if (verifiedAccount == null) {
            redirectView.setUrl("http://localhost:3000/login");
        } else {
            redirectView.setUrl("http://localhost:3000/accounts");
        }
        return redirectView;
    }

    @GetMapping("/email")
    public RedirectView verifyAccountEmail() throws IOException {
        Account verifiedAccount = loginService.verifyAccountEmail();
        RedirectView redirectView = new RedirectView();
        if (verifiedAccount == null) {
            redirectView.setUrl("http://localhost:3000/login");
        } else {
            redirectView.setUrl("http://localhost:3000/accounts");
        }
        return redirectView;
    }

}
