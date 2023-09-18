package com.example.saloon.controller;

import com.example.saloon.entity.Users;
import com.example.saloon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Users());
        model.addAttribute("nameTooltip", "Введите ваше имя");
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid Users user, BindingResult bindingResult,
                               @RequestParam("birthday") LocalDate birthday) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        Optional<Users> existingUser = Optional.ofNullable(userService.findByLogin(user.getLogin()));
        if (existingUser.isPresent()) {
            bindingResult.rejectValue("login", "error.user", "An account already exists for this login.");
            return "registration";
        }

        user.setBirthDay(birthday);
        userService.createUser(user);
        return "redirect:/login";
    }
}
