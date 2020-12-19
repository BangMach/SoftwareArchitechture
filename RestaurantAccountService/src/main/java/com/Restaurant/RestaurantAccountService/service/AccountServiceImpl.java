package com.Restaurant.RestaurantAccountService.service;

import com.Restaurant.RestaurantAccountService.helper.SecurityHelper;
import com.Restaurant.RestaurantAccountService.DAO.AccountDAOInterface;
import com.Restaurant.RestaurantAccountService.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountServiceInterface {

    private AccountDAOInterface accountDAO;

    @Autowired
    public AccountServiceImpl(@Qualifier("accountDAOImpl") AccountDAOInterface accountDAO){
        this.accountDAO = accountDAO;
    }

    @Override
    @Transactional
    public Account createAccount(Account account) {
        if (account.getUsername() != null) {
            List<Account> foundAccounts = accountDAO.findAccountByUsername(account.getUsername());
            if (foundAccounts.size() == 0) {
                if (account.getEmail() != null) {
                    foundAccounts = accountDAO.findAccountByEmail(account.getEmail());
                    if (foundAccounts.size() == 0) {
                        if (account.getPassword() != null) {
                            account.setPassword(SecurityHelper.encryptPassword((account.getPassword())));
                            return accountDAO.saveAccount(account);
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    @Transactional
    public Account findAccountByUsername(String username)  {
        List<Account> foundAccounts = accountDAO.findAccountByUsername(username);
        if (foundAccounts.size() > 0) {
            return foundAccounts.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public Account findAccountByEmail(String email) {
        List<Account> foundAccounts = accountDAO.findAccountByEmail(email);
        if (foundAccounts.size() > 0) {
            return foundAccounts.get(0);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public List<Account> findAccounts(Account account) {
        return accountDAO.findAccounts(account);
    }

    @Override
    @Transactional
    public List<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }

    @Override
    @Transactional
    public Account updateAccount(Account account)  {
        Account currentAccount = findAccountById(account.getId());
        if (currentAccount != null) {
                if (account.getUsername() != null) {
                    currentAccount.setUsername(account.getUsername());
                }
                if (account.getPassword() != null) {
                    currentAccount.setPassword(SecurityHelper.encryptPassword((account.getPassword())));
                }
                if (account.getFullName() != null) {
                    currentAccount.setFullName(account.getFullName());
                }
                if (account.getEmail() != null) {
                    currentAccount.setEmail(account.getEmail());
                }
                if (account.getPhone() != null) {
                    currentAccount.setPhone(account.getPhone());
                }
                if (account.getAddress() != null) {
                    currentAccount.setAddress(account.getAddress());
                }
            return accountDAO.saveAccount(currentAccount);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public String deleteAccountById(int id) {
        Account currentAccount = findAccountById(id);
        if (currentAccount != null) {
            accountDAO.deleteAccountById(id);
            return "deleted account id " + id;
        } else {
            return "Account Id not found";
        }
    }

    @Override
    @Transactional
    public Account findAccountById(int id) {
        return accountDAO.findAccountById(id);
    }

}
