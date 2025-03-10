package com.study.Board.user.controller;

import com.study.Board.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        //model.addAttribute("loginDto", new LoginDto());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String userId, @RequestParam String password) {
        log.info("로그인 시도 - userId: {}", userId);
        log.info("로그인 시도 - password: {}", password);
        System.out.println("🔥🔥🔥 로그인 요청이 들어왔습니다!");
        return "redirect:/";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        System.out.println("Logged in user: " + user.getUsername());
        return "redirect:/";
    }

    @GetMapping("/loginFailure")
    public String loginFailure() {
        return "login?error=true";
    }
}
