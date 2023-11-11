package com.example.saloon.registration;

import com.example.saloon.member.Member;
import com.example.saloon.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Controller
public class RegistrationController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Member());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid Member user, BindingResult bindingResult,
                               Model model, @RequestParam("birthdate") String birthdate) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "registration";
        }

        if (memberService.loginIsExist(user.getLogin())) {
            bindingResult.rejectValue("login", "error.user", "An account already exists for this login.");
            model.addAttribute("user", user);
            return "registration";
        }

        user.setBirthDay(LocalDate.parse(birthdate));
        memberService.createMember(user);
        return "redirect:/login";
    }

    @GetMapping("/check-login")
    @ResponseBody
    public Map<String, Boolean> checkLogin(@RequestParam String login) {
        boolean loginExists = memberService.loginIsExist(login);
        return Collections.singletonMap("loginExists", loginExists);
    }
}
