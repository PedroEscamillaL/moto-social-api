package com.motos.api.controllers;

import com.motos.api.models.Comment;
import com.motos.api.models.Post;
import com.motos.api.models.User;
import com.motos.api.repositories.CommentRepository;
import com.motos.api.repositories.PostRepository;
import com.motos.api.repositories.UserRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin("*")
public class CommentController {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    public CommentController(
            CommentRepository commentRepository,
            PostRepository postRepository,
            UserRepository userRepository
    ) {

        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{postId}")
    public List<Comment> getComments(
            @PathVariable Long postId
    ) {

        Optional<Post> post =
                postRepository.findById(postId);

        return commentRepository.findByPost(
                post.get()
        );
    }

    @PostMapping
    public Comment createComment(
            @RequestBody Map<String, String> body
    ) {

        Long postId =
                Long.parseLong(body.get("postId"));

        Long userId =
                Long.parseLong(body.get("userId"));

        String text =
                body.get("text");

        Post post =
                postRepository.findById(postId)
                        .orElseThrow();

        User user =
                userRepository.findById(userId)
                        .orElseThrow();

        Comment comment = new Comment(
                text,
                post,
                user
        );

        return commentRepository.save(
                comment
        );
    }
}