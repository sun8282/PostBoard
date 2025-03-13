package com.study.Board.post.service;

import com.study.Board.post.dto.PostDto;
import com.study.Board.post.entity.Post;
import com.study.Board.post.repository.PostRepository;
import com.study.Board.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final String SAVE_DIR = "C:\\Users\\user\\Desktop\\image\\postImage\\";
    private final String UPLOAD_DIR = "/image/postImage/";

    public String uploadImage(MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            return null;
        }

        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path path = Paths.get(SAVE_DIR + filename);

        Files.createDirectories(path.getParent());

        Files.write(path, file.getBytes());

        return UPLOAD_DIR + filename;
    }

    public void createPost(PostDto postDto, User user) {

        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setCategory(postDto.getCategory());
        post.setPostProfileImage(postDto.getPostProfileImage());
        post.setUser(user);

        postRepository.save(post);
    }
}
