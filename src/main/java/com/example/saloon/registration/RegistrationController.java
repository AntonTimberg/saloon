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

import javax.validation.Valid;
import java.time.LocalDate;
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
                               @RequestParam("birthdate") String birthdate) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        Optional<Member> existingUser = Optional.ofNullable(memberService.findByLogin(user.getLogin()));
        if (existingUser.isPresent()) {
            bindingResult.rejectValue("login", "error.user", "An account already exists for this login.");
            return "registration";
        }

        user.setBirthDay(LocalDate.parse(birthdate));
        memberService.createMember(user);
        return "redirect:/login";
    }
}
