package com.Restaurant.RestaurantLoginService.service;

import com.Restaurant.RestaurantLoginService.helper.HttpHelper;
import com.Restaurant.RestaurantLoginService.helper.SecurityHelper;
import com.Restaurant.RestaurantLoginService.model.Account;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class LoginServiceImpl implements LoginServiceInterface {

    @Override
    @Transactional
    public Account verifyAccountPassword(Account account) throws IOException {
        String url = "accounts/find/username?username=" + account.getUsername();
        Account foundAccount = HttpHelper.setUpGetConnection(url);
        if (foundAccount != null) {
            if (SecurityHelper.verifyPassword(account.getPassword(), foundAccount.getPassword())) {
                return foundAccount;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public Account verifyAccountEmail() throws IOException {
        String verifiedEmail = (String)((DefaultOidcUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getAttributes()
                .get("email");
        SecurityContextHolder.getContext().setAuthentication(null);
        String url = "accounts/find/email?email=" + verifiedEmail;
        return HttpHelper.setUpGetConnection(url);
    }

}
