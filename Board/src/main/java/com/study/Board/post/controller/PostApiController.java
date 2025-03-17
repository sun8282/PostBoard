package com.study.Board.post.controller;

import com.study.Board.post.util.Base64Util;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostApiController {

    @PostMapping("/upload-image")
    public ResponseEntity<String> submitPost(@RequestParam("content") String content) {
        try {
            String imageUrl = Base64Util.saveBase64Image(content);
            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Image upload failed");
        }
    }

}

