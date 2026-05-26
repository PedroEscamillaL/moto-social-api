package com.motos.api.controllers;

import com.motos.api.models.Post;
import com.motos.api.repositories.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }
 
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
    }

    @PutMapping("/{id}/like")
    public Post likePost(@PathVariable Long id) {

        Post post = postRepository.findById(id)
            .orElseThrow();

        post.setLikes(post.getLikes() + 1);

        return postRepository.save(post);
    }



}
