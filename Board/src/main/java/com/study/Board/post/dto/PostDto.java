package com.study.Board.post.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {

    @NotBlank(message = "title is mandatory")
    private String title;

    @NotBlank(message = "content is mandatory")
    private String content;

    @NotBlank(message = "category is mandatory")
    private String category;

    private String postProfileImage;
}
