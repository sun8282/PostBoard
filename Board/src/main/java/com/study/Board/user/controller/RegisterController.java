package com.study.Board.user.controller;

import com.study.Board.user.dto.UserDto;
import com.study.Board.user.service.ProfileService;
import com.study.Board.user.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;
    private final ProfileService profileService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userDto") @Valid UserDto userDto,
                               BindingResult bindingResult,
                               @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) throws IOException {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        String profileImagePath = profileService.saveProfileImage(profileImage);

        userService.registerUser(userDto, profileImagePath);

        return "redirect:/login";
    }
}
