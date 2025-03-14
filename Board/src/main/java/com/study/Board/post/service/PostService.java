package com.study.Board.post.service;

import com.study.Board.post.dto.PostDto;
import com.study.Board.post.entity.Post;
import com.study.Board.post.repository.PostRepository;
import com.study.Board.user.entity.User;
import com.study.Board.user.service.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final String SAVE_DIR = "C:\\Users\\user\\Desktop\\image\\postImage\\";
    private final String UPLOAD_DIR = "/image/postImage/";

    public String uploadImage(MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            return UPLOAD_DIR+"default-profile.png";
        }

        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path path = Paths.get(SAVE_DIR + filename);

        Files.createDirectories(path.getParent());

        Files.write(path, file.getBytes());

        return UPLOAD_DIR + filename;
    }

    public void createPost(PostDto postDto, @AuthenticationPrincipal CustomUserDetails currentUser, String profileImagePath) {

        Post newPost = postDto.toEntity(currentUser, profileImagePath);

        postRepository.save(newPost);
    }

    public List<Post> getAllPosts() {
        List<Post> postArr = postRepository.findAll();
        for (Post post : postArr) {

        }
        return postRepository.findAll();
    }
}
