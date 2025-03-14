package com.study.Board.post.controller;

import com.study.Board.post.service.Base64Service;
import com.study.Board.post.service.PostService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;
    private final Base64Service base64Service;

    @PostMapping("/upload-image")
    @ResponseBody
    public Map<String, String> uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        String imageUrl = postService.uploadImage(file);
        System.out.println(imageUrl);
        return Collections.singletonMap("url", imageUrl);
    }

}

