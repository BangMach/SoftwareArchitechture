package com.Restaurant.RestaurantLoginService.helper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityHelper {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static Boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
