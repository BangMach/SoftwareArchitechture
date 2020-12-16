package com.Restaurant.RestaurantLoginService.service;

import com.Restaurant.RestaurantLoginService.helper.SecurityHelper;
import com.Restaurant.RestaurantLoginService.DAO.AccountDAOInterface;
import com.Restaurant.RestaurantLoginService.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
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
    public Account verifyAccountPassword(Account account) {
        List<Account> foundAccounts = accountDAO.findAccountByUsername(account.getUsername());
        if (foundAccounts.size() > 0) {
            Account foundAccount = foundAccounts.get(0);
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
