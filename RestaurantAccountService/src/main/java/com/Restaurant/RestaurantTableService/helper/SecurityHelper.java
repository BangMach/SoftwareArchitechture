package com.Restaurant.RestaurantTableService.helper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityHelper {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encryptPassword(String plainText) {
        return passwordEncoder.encode(plainText);
    }

}
