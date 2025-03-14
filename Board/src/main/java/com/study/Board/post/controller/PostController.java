package com.study.Board.post.controller;

import com.study.Board.post.dto.PostDto;
import com.study.Board.post.repository.PostRepository;

import com.study.Board.post.service.Base64Service;
import com.study.Board.post.service.PostService;
import com.study.Board.user.entity.User;
import com.study.Board.user.service.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;

    @GetMapping("/new")
    public String showPostForm(Model model) {

        model.addAttribute("postDto", new PostDto());
        return "createPost";
    }

    @PostMapping("")
    public String createPost(@ModelAttribute PostDto postDto, @AuthenticationPrincipal CustomUserDetails currentUser, BindingResult bindingResult,
                             @RequestParam(value = "postProfileImage", required = false) MultipartFile postProfileImage) throws IOException{

        System.out.println("Received title: " + postDto.getTitle());
        System.out.println("Received content: " + postDto.getContent());

        String profileImagePath = postService.uploadImage(postProfileImage);
        postService.createPost(postDto, currentUser, profileImagePath);
        return "redirect:/";
    }

    @PostMapping("/upload-image-endpoint")
    public ResponseEntity<Map<String, String>> base64Upload(@RequestParam("image") MultipartFile file) {
        Map<String, String> response = new HashMap<>();

        try {
            // 파일 업로드 처리 (이미지 저장)
            String imageUrl = postService.uploadImage(file); // 업로드 후 이미지 URL 반환
            response.put("url", imageUrl); // 응답에 이미지 URL 포함
        } catch (IOException e) {
            response.put("error", "Image upload failed");
        }

        return ResponseEntity.ok(response); // 응답 반환
    }
}
