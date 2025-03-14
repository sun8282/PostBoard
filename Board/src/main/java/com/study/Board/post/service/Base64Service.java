package com.study.Board.post.service;

import com.study.Board.post.entity.Post;
import com.study.Board.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class Base64Service {
    private final PostRepository postRepository;
    private final String SAVE_DIR = "C:\\Users\\user\\Desktop\\image\\postImage\\";
    private final String UPLOAD_DIR = "/image/postImage/";

    public String saveBase64Image(String base64Image) throws IOException {
        String[] parts = base64Image.split(",");
        String imageData = parts[1];
        String mimeType = parts[0].split(";")[0].split(":")[1];  // 예: image/png
        String fileExtension = mimeType.split("/")[1]; // "png", "jpeg" 등

        byte[] imageBytes = Base64.getDecoder().decode(imageData); // base64 디코딩
        String fileName = UUID.randomUUID().toString() + "." + fileExtension; // 확장자를 동적으로 설정
        Path filePath = Paths.get(SAVE_DIR, fileName);

        Files.createDirectories(Paths.get(SAVE_DIR));
        Files.write(filePath, imageBytes);

        return UPLOAD_DIR + fileName;
    }



}
