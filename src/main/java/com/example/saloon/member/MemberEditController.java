package com.example.saloon.member;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class MemberEditController {
    @Autowired
    MemberRepo memberRepo;

    @Autowired
    MemberService memberService;

    @GetMapping("/members/edit")
    public String editMemberForm(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Member member = memberRepo.findByLogin(username);

            if (member != null) {
                model.addAttribute("member", member);
            }
        }
        return "edit-member";
    }

    @PostMapping("/members/edit")
    public String updateMember(@RequestParam String password,
                               @RequestParam String confirmPassword,
                               Authentication authentication,
                               Model model) {

        String username = authentication.getName();
        Member member = memberService.findByLogin(username);

        if (member == null) {
            model.addAttribute("updateError", "Пользователь не найден");
            return "edit-member";
        }

        model.addAttribute("member", member);

        if (!password.equals(confirmPassword)) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "edit-member";
        }

        member.setPassword(password);
        memberService.updateMember(member);

        model.addAttribute("successMessage", "Пароль успешно изменен");
        return "edit-member";
    }
}
