package com.study.Board.user.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ProfileService {

    private final String UPLOAD_DIR = "src/main/resources/static/uploads/profileImages/";
    //private final String UPLOAD_DIR = "/uploads/profileImages/";
    // private final String UPLOAD_DIR = "C:\\Users\\user\\Desktop\\image\\";
    public String saveProfileImage(MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return null;
        }
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + fileName);

        Files.createDirectories(path.getParent());

        Files.write(path, file.getBytes());
        System.out.println("프로필 이미지 저장");
        return UPLOAD_DIR+fileName;
    }
}
