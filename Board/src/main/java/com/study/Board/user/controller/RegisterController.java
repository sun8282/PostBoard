package com.study.Board.user.controller;

import com.study.Board.user.dto.UserDto;
import com.study.Board.user.entity.User;
import com.study.Board.user.service.ProfileService;
import com.study.Board.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;
    private final ProfileService profileService;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userDto") @Valid UserDto userDto, BindingResult bindingResult, @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        String profileImagePath = saveProfileImage(userDto.getProfileImage());

        User user = userDto.toEntity(passwordEncoder,profileImagePath);
        userService.registerUser(user);

        return "redirect:/login";
    }
    private String saveProfileImage(MultipartFile profileImage) {
        if (profileImage != null && !profileImage.isEmpty()) {
            try {
                String uploadDir = "src/main/resources/static/uploads/";

                String originalFilename = profileImage.getOriginalFilename();
                Path uploadPath = Path.of(uploadDir, originalFilename);

                Files.copy(profileImage.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);

                return "/uploads/" + originalFilename;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
