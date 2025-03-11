package com.study.Board.user.controller;

import com.study.Board.user.repository.UserRepository;
import com.study.Board.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.study.Board.user.entity.User;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user/{userId}")
    public String userDetails(@PathVariable("userId") String userId, Model model) {
        User user = userService.findByUserId(userId);
        if (user == null) {
            return "error/404";
        }
        model.addAttribute("user", user);
        return "userDetails";
    }
}
