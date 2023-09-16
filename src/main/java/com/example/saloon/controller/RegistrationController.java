package com.example.saloon.controller;

import com.example.saloon.entity.Users;
import com.example.saloon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid Users user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        Optional<Users> existingUser = Optional.ofNullable(userService.findByLogin(user.getLogin()));
        if (existingUser.isPresent()) {
            bindingResult.rejectValue("login", "error.user", "An account already exists for this login.");
            return "registration";
        }

        userService.createUser(user);
        return "redirect:/login";
    }
}
