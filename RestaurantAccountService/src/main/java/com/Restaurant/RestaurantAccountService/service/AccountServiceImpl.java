package com.Restaurant.RestaurantAccountService.service;

import com.Restaurant.RestaurantAccountService.DAO.AccountDAOInterface;
import com.Restaurant.RestaurantAccountService.helper.SecurityHelper;
import com.Restaurant.RestaurantAccountService.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountServiceInterface {

    private final AccountDAOInterface accountDAO;

    @Autowired
    public AccountServiceImpl(@Qualifier("accountDAOImpl") AccountDAOInterface accountDAO){
        this.accountDAO = accountDAO;
    }

    @Override
    @Transactional
    public Account createAccount(Account account) {
        String username = account.getUsername();
        if (username != null && !username.equals("")) {
            List<Account> foundAccounts = accountDAO.findAccountByUsername(username);
            if (foundAccounts.size() == 0) {
                account.setUsername(username.trim());
                String email = account.getEmail();
                if (email != null && !email.equals("")) {
                    foundAccounts = accountDAO.findAccountByEmail(email);
                    if (foundAccounts.size() == 0) {
                        account.setEmail(email.trim());
                        String password = account.getPassword();
                        if (password != null && !password.equals("")) {
                            account.setPassword(SecurityHelper.encryptPassword(password));
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
    public List<Account> findAccounts(Account account, int startAt, int maxResults) {
        return accountDAO.findAccounts(account, startAt, maxResults);
    }

    @Override
    @Transactional
    public List<Account> getAllAccounts(int startAt, int maxResults) {
        return accountDAO.getAllAccounts(startAt, maxResults);
    }

    @Override
    @Transactional
    public Account updateAccount(Account account)  {
        Account currentAccount = findAccountById(account.getId());
        if (currentAccount != null) {
            String username = account.getUsername();
            if (username != null && !username.equals("") && !username.equals(currentAccount.getUsername())) {
                List<Account> foundAccounts = accountDAO.findAccountByUsername(username);
                if (foundAccounts.size() == 0) {
                    currentAccount.setUsername(username.trim());
                }
            }
            String password = account.getPassword();
            if (password != null && !password.equals("")) {
                currentAccount.setPassword(SecurityHelper.encryptPassword(password));
            }
            String email = account.getEmail();
            if (email != null && !email.equals("") && !email.equals(currentAccount.getEmail())) {
                List<Account> foundAccounts = accountDAO.findAccountByEmail(email);
                if (foundAccounts.size() == 0) {
                    currentAccount.setEmail(email.trim());
                }
            }
            String fullName = account.getFullName();
            if (fullName != null) {
                currentAccount.setFullName(fullName);
            }
            String phone = account.getPhone();
            if (phone != null) {
                currentAccount.setPhone(phone);
            }
            String address = account.getAddress();
            if (address != null) {
                currentAccount.setAddress(address);
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
            return "Deleted account id " + id;
        } else {
            return "Account id not found";
        }
    }

    @Override
    @Transactional
    public Account findAccountById(int id) {
        return accountDAO.findAccountById(id);
    }

    @Override
    @Transactional
    public Account verifyAccountPassword(Account account) {
        Account foundAccount = findAccountByUsername(account.getUsername());
        if (foundAccount != null) {
            if (SecurityHelper.verifyPassword(account.getPassword(), foundAccount.getPassword())) {
                return foundAccount;
            }
        }
        return null;
    }

}
