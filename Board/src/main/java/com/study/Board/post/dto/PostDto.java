package com.study.Board.post.dto;

import com.study.Board.post.entity.Post;
import com.study.Board.user.entity.User;
import com.study.Board.user.service.CustomUserDetails;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PostDto {

    @NotBlank(message = "title is mandatory")
    private String title;

    @NotBlank(message = "content is mandatory")
    private String content;

    @NotBlank(message = "category is mandatory")
    private String category;

    private MultipartFile postProfileImage;

    public Post toEntity(CustomUserDetails currentUser, String profileImagePath) {
        return Post.builder()
                .title(title)
                .content(content)
                .category(category)
                .user(currentUser.getUser())
                .postProfileImage(profileImagePath)
                .build();
    }
}
