package com.study.Board.user.controller;

import com.study.Board.user.dto.UserDto;
import com.study.Board.user.service.CustomUserDetails;
import com.study.Board.user.service.ProfileService;
import com.study.Board.user.service.UserService;

import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ProfileService profileService;

    @GetMapping("/{userId}")
    public String userDetails(Model model, @AuthenticationPrincipal CustomUserDetails currentUser) {
        model.addAttribute("currentUser", currentUser);
        return "userDetails";
    }

    @GetMapping("/{userId}/edit")
    public String userEdit(Model model, @AuthenticationPrincipal CustomUserDetails currentUser) {
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("currentUser", currentUser);
        return "userEdit";
    }

    @PatchMapping("/{userId}")
    public String editResponse(Model model, @AuthenticationPrincipal CustomUserDetails currentUser,
                               @ModelAttribute("userDto") @Valid UserDto userDto,
                               BindingResult bindingResult,
                               @RequestParam(value = "profileImage", required = false) MultipartFile profileImage
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            model.addAttribute("userDto", new UserDto());
            model.addAttribute("currentUser", currentUser);
            return "userEdit";
        }
        String profileImagePath = profileService.saveProfileImage(profileImage);
        userService.updateUser(userDto, profileImagePath);
        return "redirect:/login";
    }
}
