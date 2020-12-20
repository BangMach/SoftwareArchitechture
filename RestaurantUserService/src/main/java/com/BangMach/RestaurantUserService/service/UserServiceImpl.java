package com.BangMach.RestaurantUserService.service;

import com.BangMach.RestaurantUserService.DAO.UserDAOInterface;
import com.BangMach.RestaurantUserService.model.RestaurantTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Service
public class UserServiceImpl implements UserServiceInterface {

    private UserDAOInterface userDAO;

    @Autowired
    public UserServiceImpl(@Qualifier("userDAOImpl") UserDAOInterface userDAO){
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public List<RestaurantTable> searchAvailableTables(Timestamp startTime) {
        return userDAO.searchAvailableTable(startTime);
    }

}
