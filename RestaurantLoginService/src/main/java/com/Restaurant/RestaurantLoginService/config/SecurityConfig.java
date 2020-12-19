package com.Restaurant.RestaurantLoginService.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity httpSecurity) throws  Exception {
        httpSecurity
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .oauth2Login();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/login/password");
    }

}
