package com.example.saloon.registration;

import com.example.saloon.member.Member;
import com.example.saloon.member.MemberController;
import com.example.saloon.member.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
import java.time.Period;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/firstPage";
        }
        model.addAttribute("user", new Member());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid Member user, BindingResult bindingResult,
                               Model model, @RequestParam("birthdate") String birthdate, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/firstPage";
        }

        LocalDate birthDate = LocalDate.parse(birthdate);
        user.setBirthDay(birthDate);
        if (!isAgeValid(birthDate)) {
            model.addAttribute("ageError", "Сервис доступен только после 14 лет");
            model.addAttribute("user", user);
            return "registration";
        }

        if (bindingResult.hasErrors()) {

            model.addAttribute("user", user);
            return "registration";
        }

        if (memberService.loginIsExist(user.getLogin())) {
            logger.info("Login already exists: " + user.getLogin());
            bindingResult.rejectValue("login", "error.user", "An account already exists for this login.");
            model.addAttribute("user", user);
            return "registration";
        }

        memberService.createMember(user);
        return "redirect:/login";
    }

    @GetMapping("/check-login")
    @ResponseBody
    public Map<String, Boolean> checkLogin(@RequestParam String login) {
        boolean loginExists = memberService.loginIsExist(login);
        return Collections.singletonMap("loginExists", loginExists);
    }

    private boolean isAgeValid(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears() >= 14;
    }
}
