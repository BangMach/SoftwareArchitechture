package com.Restaurant.RestaurantLoginService.service;

import com.Restaurant.RestaurantLoginService.DAO.AccountDAOInterface;
import com.Restaurant.RestaurantLoginService.helper.HttpHelper;
import com.Restaurant.RestaurantLoginService.helper.SecurityHelper;
import com.Restaurant.RestaurantLoginService.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginServiceInterface {

    private AccountDAOInterface accountDAO;

    @Autowired
    public LoginServiceImpl(@Qualifier("accountDAOImpl") AccountDAOInterface accountDAO){
        this.accountDAO = accountDAO;
    }

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
    public Account verifyAccountEmail(String email) {
        List<Account> foundAccounts = accountDAO.findAccountByEmail(email);
        if (foundAccounts.size() > 0) {
            return foundAccounts.get(0);
        } else {
            return null;
        }
    }

}
