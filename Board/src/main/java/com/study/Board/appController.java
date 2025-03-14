package com.study.Board;

import com.study.Board.post.entity.Post;
import com.study.Board.post.repository.PostRepository;
import com.study.Board.user.entity.User;
import com.study.Board.user.repository.UserRepository;
import com.study.Board.user.service.CustomUserDetails;

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

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @GetMapping("/")
    public String showHome(Model model,  @AuthenticationPrincipal CustomUserDetails currentUser) {
        User findUser = userRepository.findByUserId(currentUser.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User with ID " + currentUser.getUserId() + " not found."));
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("currentUser", findUser);
        return "index";
    }
}
