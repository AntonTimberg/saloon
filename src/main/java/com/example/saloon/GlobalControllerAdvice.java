package com.example.saloon;

import com.example.saloon.member.Member;
import com.example.saloon.member.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private MemberRepo memberRepo;

    @ModelAttribute
    public void addAttributes(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Member member = memberRepo.findByLogin(username);
            if (member != null) {
                model.addAttribute("userFirstName", member.getName());
                model.addAttribute("userLastName", member.getSurname());
            }
        }
    }
}
