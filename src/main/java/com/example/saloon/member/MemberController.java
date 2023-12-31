package com.example.saloon.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;
    private final MemberConverter memberConverter;

    @RequestMapping ("/users/getAll")
    public List<MemberDto> getAllUsers(){
        return memberService.getAll().stream()
                .map(memberConverter::convert)
                .toList();
    }

//    @GetMapping("/deleteByLogin/{login}")
//    public ResponseEntity<String> deleteByLogin(@PathVariable String login){
//        Member user = memberService.findByLogin(login);
//        if(user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        else memberService.deleteMemberByLogin(user.getLogin());
//        return ResponseEntity.ok("User deleted successfully");
//    }

    @GetMapping("/users/{login}/getStatus")
    public ResponseEntity<String> getStatus(@PathVariable String login){
        Member user = memberService.findByLogin(login);
        if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        else return ResponseEntity.ok(String.valueOf(user.getStatus()));
    }
}
