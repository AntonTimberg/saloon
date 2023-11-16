package com.example.saloon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


@SpringBootApplication
public class SaloonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaloonApplication.class, args);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector handlerMappingIntrospector) throws Exception {
        MvcRequestMatcher welcomeMatcher = new MvcRequestMatcher(handlerMappingIntrospector, "/");
        MvcRequestMatcher registerMatcher = new MvcRequestMatcher(handlerMappingIntrospector, "/register");
        MvcRequestMatcher loginMatcher = new MvcRequestMatcher(handlerMappingIntrospector, "/login");
        MvcRequestMatcher stylesMatcher = new MvcRequestMatcher(handlerMappingIntrospector, "/styles.css");

        return http
                .csrf().disable()
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(welcomeMatcher, registerMatcher, stylesMatcher, loginMatcher).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/register")
                        .permitAll()
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
