package com.study.Board.user.controller;

import com.study.Board.user.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
