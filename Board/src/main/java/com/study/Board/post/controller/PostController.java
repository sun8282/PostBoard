package com.study.Board.post.controller;

import com.study.Board.post.entity.Post;
import com.study.Board.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PostController {

    private final PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @GetMapping("/")
    public String showIndex(Model model){
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "index";
    }
}
