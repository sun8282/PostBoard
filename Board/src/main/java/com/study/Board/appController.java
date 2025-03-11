package com.study.Board;

import com.study.Board.post.entity.Post;
import com.study.Board.post.repository.PostRepository;
import com.study.Board.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class appController {

    private final PostRepository postRepository;

    @Autowired
    public appController(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @GetMapping("/")
    public String showHome(Model model){
        UserDetails currentUser = SecurityUtil.getCurrentUser();
        System.out.println(currentUser.getUsername());
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("currentUser",currentUser);
        return "index";
    }
}
