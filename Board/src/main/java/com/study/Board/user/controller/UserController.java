package com.study.Board.user.controller;

import com.study.Board.user.service.CustomUserDetails;
import com.study.Board.user.service.UserService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{userId}")
    public String userDetails(Model model, @AuthenticationPrincipal CustomUserDetails currentUser) {
        model.addAttribute("currentUser", currentUser);
        return "userDetails";
    }
}
