package com.example.saloon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


@SpringBootApplication
@EnableScheduling
public class SaloonApplication {
    public static void main(String[] args) {
        SpringApplication.run(SaloonApplication.class, args);
    }

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector handlerMappingIntrospector) throws Exception {
        MvcRequestMatcher welcomeMatcher = new MvcRequestMatcher(handlerMappingIntrospector, "/");
        MvcRequestMatcher registerMatcher = new MvcRequestMatcher(handlerMappingIntrospector, "/register");
        MvcRequestMatcher loginMatcher = new MvcRequestMatcher(handlerMappingIntrospector, "/login");
        MvcRequestMatcher stylesMatcher = new MvcRequestMatcher(handlerMappingIntrospector, "/styles.css");
        MvcRequestMatcher getAllMatcher = new MvcRequestMatcher(handlerMappingIntrospector, "/users/getAll");
        MvcRequestMatcher api = new MvcRequestMatcher(handlerMappingIntrospector, "/api/**");
        MvcRequestMatcher admin = new MvcRequestMatcher(handlerMappingIntrospector, "/admin/**");

        return http
                .csrf().disable()
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(welcomeMatcher, registerMatcher, stylesMatcher, loginMatcher).permitAll()
                        .requestMatchers(api).hasRole("SERVICE")
                        .requestMatchers(getAllMatcher,admin).hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(myAuthenticationSuccessHandler)
                        .failureUrl("/login")
                        .permitAll()
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
