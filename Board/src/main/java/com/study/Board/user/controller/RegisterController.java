package com.study.Board.user.controller;

import com.study.Board.user.dto.UserDto;
import com.study.Board.user.entity.User;
import com.study.Board.user.service.ProfileService;
import com.study.Board.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

        if (profileImage != null && !profileImage.isEmpty()) {
            try {
                String savedImagePath = profileService.saveProfileImage(profileImage);
                userDto.setProfileImage(savedImagePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        User user = userDto.toEntity(passwordEncoder);
        userService.registerUser(user);

        return "redirect:/login";
    }

}
