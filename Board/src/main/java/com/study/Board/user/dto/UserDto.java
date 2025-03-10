package com.study.Board.user.dto;

import com.study.Board.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Please provide a valid email")
    private String userEmail;

    @NotBlank(message = "Username is mandatory")
    private String userName;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "User ID is mandatory")
    private String userId;

    public User toEntity(BCryptPasswordEncoder passwordEncoder) {
        User user = new User();
        user.setUserEmail(this.userEmail);
        user.setUserName(this.userName);
        user.setUserId(this.userId);
        user.setPassword(passwordEncoder.encode(this.password)); // 비밀번호 암호화
        return user;
    }
}

