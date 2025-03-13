package com.study.Board.user.service;

import com.study.Board.user.dto.UserDto;
import com.study.Board.user.entity.User;
import com.study.Board.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(UserDto userDto, String profileImagePath) {

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        User newUser = userDto.toEntity(encodedPassword, profileImagePath);

        userRepository.save(newUser);
    }

    @Transactional
    public void updateUser(UserDto userDto, String profileImagePath) {

        User findUser = userRepository.findByUserId(userDto.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User with ID " + userDto.getUserId() + " not found."));
        findUser.updateInfo(userDto, profileImagePath);
    }
}

