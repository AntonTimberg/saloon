package com.example.saloon.securityAndAuthorization;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class WelcomeController {

    @GetMapping("/")
    public String welcome(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/firstPage";
        }
        return "welcome";
    }

    @GetMapping("/firstPage")
    public String firstPage() {
        return "firstPage";
    }
}
