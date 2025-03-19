package com.study.Board;

import com.study.Board.post.entity.Post;
import com.study.Board.post.service.PostService;
import com.study.Board.user.entity.User;
import com.study.Board.user.service.ProfileService;
import com.study.Board.user.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class appController {

    private final UserService userService;
    private final ProfileService profileService;
    private final PostService postService;

    @GetMapping("/")
    public String showHome(@RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "10") int size,
                           Model model) {
        User findUser = userService.findCurrentUser();
        Page<Post> posts = postService.getAllPosts(page, size);

        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", posts.getNumber());
        model.addAttribute("totalPages", posts.getTotalPages());

        model.addAttribute("currentUser", findUser);
        return "index";
    }
}
