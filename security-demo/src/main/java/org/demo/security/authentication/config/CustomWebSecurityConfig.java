package org.demo.security.authentication.config;


import org.demo.security.filter.MyCustomFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.Security;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfig {

    @Bean
    public SecurityFilterChain publicApiFilterChain(HttpSecurity http) throws Exception {

        http.
            authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());

        http.addFilterBefore(new MyCustomFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
