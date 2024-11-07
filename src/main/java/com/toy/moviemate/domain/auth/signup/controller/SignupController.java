package com.toy.moviemate.domain.auth.signup.controller;

import com.toy.moviemate.domain.user.dto.UserDto;
import com.toy.moviemate.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignupController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signup() {
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String signup(UserDto userDto) {
        userService.createUser(userDto);
        return "redirect:/";
    }
}
