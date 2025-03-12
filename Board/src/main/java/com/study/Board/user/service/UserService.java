package com.study.Board.user.service;

import com.study.Board.user.dto.UserDto;
import com.study.Board.user.entity.User;
import com.study.Board.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(UserDto userDto, String profileImagePath) {

        User user = new User();
        setUserInfo(userDto, profileImagePath, user);

        userRepository.save(user);
    }

    public void updateUser(UserDto userDto, String profileImagePath) {

        Optional<User> optionalUser = userRepository.findByUserId(userDto.getUserId());
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User with ID " + userDto.getUserId() + " not found.");
        }
        User user = optionalUser.get();
        setUserInfo(userDto, profileImagePath, user);

        userRepository.save(user);
    }

    private void setUserInfo(UserDto userDto, String profileImagePath, User user) {

        user.setUserId(userDto.getUserId());
        user.setUserName(userDto.getUserName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUserEmail(userDto.getUserEmail());
        user.setProfileImage(profileImagePath != null ? "/uploads/profileImages" + profileImagePath : "/uploads/profileImages/default-profile.png");
    }
}

