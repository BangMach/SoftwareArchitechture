package com.Restaurant.RestaurantAccountService.helper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityHelper {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encryptPassword(String plainText) {
        return passwordEncoder.encode(plainText);
    }

    public static Boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
