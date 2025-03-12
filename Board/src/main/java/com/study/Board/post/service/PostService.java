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

    private static final String UPLOAD_DIR = "uploads/";

    public String uploadImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return null;
        }

        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + filename);

        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());

        return "/uploads/" + filename;
    }
}
