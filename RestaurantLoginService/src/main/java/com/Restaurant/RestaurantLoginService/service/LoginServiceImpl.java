package com.Restaurant.RestaurantLoginService.service;

import com.Restaurant.RestaurantLoginService.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.transaction.Transactional;

@Service
public class LoginServiceImpl implements LoginServiceInterface {

    private final RestTemplate restTemplate;

    @Autowired
    public LoginServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Transactional
    public Account verifyAccountEmail() {
        String verifiedEmail = (String)((DefaultOidcUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getAttributes()
                .get("email");
        SecurityContextHolder.getContext().setAuthentication(null);
        String url = "http://ACCOUNT-SERVICE/accounts/email/" + verifiedEmail;
        return restTemplate.getForObject(url, Account.class);
    }

}
