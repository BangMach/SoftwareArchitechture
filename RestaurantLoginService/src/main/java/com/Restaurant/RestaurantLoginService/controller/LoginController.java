package com.Restaurant.RestaurantLoginService.controller;

import com.Restaurant.RestaurantLoginService.model.Account;
import com.Restaurant.RestaurantLoginService.service.LoginServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.io.IOException;
import java.sql.Timestamp;

@RestController
@RequestMapping(path = "/login/")
public class LoginController {

    private final LoginServiceInterface loginService;

    @Autowired
    public LoginController(LoginServiceInterface accountService){
        this.loginService = accountService;
    }

    @GetMapping("/email")
    public RedirectView verifyAccountEmail() throws IOException {
        Account verifiedAccount = loginService.verifyAccountEmail();
        RedirectView redirectView = new RedirectView();
        if (verifiedAccount == null) {
            redirectView.setUrl("http://192.168.107.145:3000/");
        } else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            redirectView.setUrl("http://192.168.107.145:3000/success/" + timestamp);
        }
        return redirectView;
    }

}
