package com.fdmgroup.onedayproject.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/**")
            .permitAll()
            .and()
            .csrf()
            .disable() // So we don't have to add csrf to every form
            .httpBasic()
            .and()
            .headers()
            .frameOptions()
            .disable(); // Allows H2 to render correctly
    }

}
