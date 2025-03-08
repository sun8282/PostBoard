package com.study.Board.user.controller;

import com.study.Board.user.dto.LoginDto;
import com.study.Board.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(LoginDto loginDto) {
        return "redirect:/home";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        System.out.println("Logged in user: " + user.getUsername());
        return "redirect:/home";
    }

    @GetMapping("/loginFailure")
    public String loginFailure() {
        return "login?error=true";
    }
}
