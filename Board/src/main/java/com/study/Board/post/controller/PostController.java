package com.study.Board.post.controller;

import com.study.Board.post.dto.PostDto;
import com.study.Board.post.entity.Post;
import com.study.Board.post.repository.PostRepository;

import com.study.Board.post.service.PostService;
import com.study.Board.user.entity.User;
import com.study.Board.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;
    private final UserService userService;

    @GetMapping("/new")
    public String showPostForm(Model model) {

        model.addAttribute("postDto", new PostDto());
        return "createPost";
    }

    @PostMapping("")
    public String createPost(@ModelAttribute PostDto postDto, BindingResult bindingResult,
                             @RequestParam(value = "postProfileImage", required = false) MultipartFile postProfileImage) throws IOException{
        String profileImagePath = postService.uploadImage(postProfileImage);
        postService.createPost(postDto, userService.findCurrentUser(), profileImagePath);
        return "redirect:/";
    }

    @GetMapping("/{postId}")
    public String postDetails(Model model, @PathVariable("postId") Long postId){

        System.out.println("postId = " + postId);
        PostDto postDto = postService.findById(postId);
        model.addAttribute("postDto", postDto);
        return "postDetails";
    }

    @GetMapping("/{postId}/edit")
    public String postEdit(Model model, @PathVariable("postId") Long postId){

        User user = userService.findCurrentUser();
        if (postService.isNotWirteUser(postId, user.getId())) {
            return "redirect:/{postId}?authentication=no";
        }

        PostDto postDto = postService.findById(postId);
        model.addAttribute("postDto", postDto);

        return "postEdit";
    }

}
