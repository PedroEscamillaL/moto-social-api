package com.motos.api.repositories;

import com.motos.api.models.Comment;
import com.motos.api.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository
        extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post post);
}