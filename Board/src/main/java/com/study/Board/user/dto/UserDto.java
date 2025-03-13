package com.study.Board.user.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


    private MultipartFile profileImage;

    public Object getProfileImage(){
        return (profileImage == null || profileImage.isEmpty()) ? "/image/profileImage/default-profile.png" : profileImage;
    }
}

