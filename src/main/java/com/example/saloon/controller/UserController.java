package com.example.saloon.controller;

import com.example.saloon.dto.UserDto;
import com.example.saloon.entity.Users;
import com.example.saloon.repo.UserRepo;
import com.example.saloon.service.UserService;
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
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserRepo userRepo;

    @RequestMapping ("/getAll")
    public List<UserDto> getAllUsers(){
        return userService.getAll();
    }

    @GetMapping("/deleteByLogin/{login}")
    public ResponseEntity<String> deleteByLogin(@PathVariable String login){
        Users user = userRepo.findByLogin(login);
        if(user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        else userRepo.delete(user);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/{login}/getStatus")
    public ResponseEntity<String> getStatus(@PathVariable String login){
        Users user = userRepo.findByLogin(login);
        if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        else return ResponseEntity.ok(String.valueOf(user.getStatus()));
    }
}
