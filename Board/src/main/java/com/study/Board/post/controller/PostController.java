package com.study.Board.post.controller;

import com.study.Board.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;

    @GetMapping("/new")
    public String showPostForm() {
        return "createPost";
    }


}
