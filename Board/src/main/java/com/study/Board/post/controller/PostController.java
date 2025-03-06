package com.study.Board.post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
    @GetMapping({"/", "/index"})
    public String showIndex(){
        return "index";
    }
}
