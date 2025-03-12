package com.study.Board.post.controller;

import com.study.Board.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;

}
