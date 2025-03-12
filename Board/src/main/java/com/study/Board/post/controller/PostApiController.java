package com.study.Board.post.controller;

import com.study.Board.post.service.PostService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    @PostMapping("/upload-image")
    @ResponseBody
    public Map<String, String> uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        String imageUrl = postService.uploadImage(file);
        System.out.println(imageUrl);
        return Collections.singletonMap("url", imageUrl);
    }
}

