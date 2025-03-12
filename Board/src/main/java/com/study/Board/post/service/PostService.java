package com.study.Board.post.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class PostService {

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/postImages/";

    public String uploadImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return null;
        }

        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + filename);

        Files.createDirectories(path.getParent());

        Files.write(path, file.getBytes());

        return "/uploads/postImages/"+filename;
    }
}
