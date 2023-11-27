package com.example.saloon.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberEditController {
    private final MemberService memberService;
    private final MemberConverter memberConverter;

    @GetMapping("/edit")
    public String editMemberForm(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Member member = memberService.findByLogin(username);

            if (member != null) {
                MemberDto memberDto = memberConverter.convert(member);
                model.addAttribute("member", memberDto);
                model.addAttribute("birthDay", member.getBirthDay());
            }
        }
        return "edit-member";
    }

    @PostMapping("/edit")
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
