package com.BangMach.RestaurantUserService.controller;

import com.BangMach.RestaurantUserService.model.RestaurantTable;
import com.BangMach.RestaurantUserService.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(path = "/users/")
public class UserController {

    private final UserServiceInterface userService;

    @Autowired
    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/available/tables")
    public List<RestaurantTable> searchAvailableTables(@RequestParam Timestamp startTime) {
        return userService.searchAvailableTables(startTime);
    }
}
