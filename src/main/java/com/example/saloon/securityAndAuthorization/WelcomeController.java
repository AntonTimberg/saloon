package com.example.saloon.securityAndAuthorization;

import com.example.saloon.member.Member;
import com.example.saloon.member.MemberRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class WelcomeController {
    @Autowired
    private final MemberRepo memberRepo;
    @GetMapping("/")
    public String welcome(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/firstPage";
        }
        return "welcome";
    }

    @GetMapping("/firstPage")
    public String firstPage(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Member member = memberRepo.findByLogin(username);
            if (member != null) {
                model.addAttribute("userFirstName", member.getName());
                model.addAttribute("userLastName", member.getSurname());
            }
        }
        return "firstPage";
    }
}
