package com.study.Board;

import com.study.Board.post.entity.Post;
import com.study.Board.post.repository.PostRepository;
import com.study.Board.post.service.PostService;
import com.study.Board.user.entity.User;
import com.study.Board.user.repository.UserRepository;
import com.study.Board.user.service.CustomUserDetails;

import com.study.Board.user.service.ProfileService;
import com.study.Board.user.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class appController {

    private final UserService userService;
    private final ProfileService profileService;
    private final PostService postService;

    @GetMapping("/")
    public String showHome(Model model,  @AuthenticationPrincipal CustomUserDetails currentUser) {
        User findUser = userService.findCurrentUser(currentUser.getUserId());
        List<Post> posts = postService.getAllPosts();

        model.addAttribute("posts", posts);
        model.addAttribute("currentUser", findUser);
        return "index";
    }
}
