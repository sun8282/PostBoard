package com.study.Board.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String filePath; // 이미지 파일 경로

    @ManyToOne
    @JoinColumn(nullable = false, name ="user_id")
    private User userId;
}
