package com.study.Board.user.service;

import com.study.Board.user.dto.RegisterDto;
import com.study.Board.user.dto.UpdateDto;
import com.study.Board.user.entity.User;
import com.study.Board.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public void registerUser(RegisterDto userDto, String profileImagePath) {

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        User newUser = userDto.toEntity(encodedPassword, profileImagePath);

        userRepository.save(newUser);
    }

    @Transactional
    public void updateUser(UpdateDto userDto, String profileImagePath) {

        User findUser = userRepository.findByUserId(userDto.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User with ID " + userDto.getUserId() + " not found."));

        findUser.updateInfo(userDto, profileImagePath);

        CustomUserDetails updatedUserDetails = new CustomUserDetails(findUser);

        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(
                updatedUserDetails,
                null,
                updatedUserDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }

    public User findCurrentUser(String userId) {
        User findUser = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User with ID " +userId + " not found."));
        return findUser;
    }
}

