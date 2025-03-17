package com.study.Board.post.repository;

import com.study.Board.post.entity.Post;
import com.study.Board.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostImageRepository extends JpaRepository<Post, Long> {
    List<Post> findByPost(Post post);
}