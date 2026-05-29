package com.motos.api.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import com.motos.api.models.Post;
import com.motos.api.models.User;

import com.motos.api.repositories.PostRepository;
import com.motos.api.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Cloudinary cloudinary;

    @GetMapping
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @PostMapping
    public Post createPost(

            @RequestParam("text") String text,

            @RequestParam("image") MultipartFile image,

            @RequestParam("username") String username

    ) throws Exception {

        User user = userRepository
                .findByUsername(username)
                .orElseThrow();

        String imageUrl = cloudinary.uploader()
                .upload(
                        image.getBytes(),
                        ObjectUtils.emptyMap()
                )
                .get("secure_url")
                .toString();

        Post post = new Post();

        post.setText(text);
        post.setImageUrl(imageUrl);
        post.setUser(user);

        return postRepository.save(post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(
            @PathVariable Long id
    ) {

        postRepository.deleteById(id);
    }

    @PutMapping("/{id}/react/{type}")
    public Post reactPost(
            @PathVariable Long id,
            @PathVariable String type
    ) {

        Post post = postRepository.findById(id)
                .orElseThrow();

        switch (type.toLowerCase()) {

            case "like":
                post.setLikes(
                        post.getLikes() + 1
                );
                break;

            case "love":
                post.setLoves(
                        post.getLoves() + 1
                );
                break;

            case "haha":
                post.setHahas(
                        post.getHahas() + 1
                );
                break;

            case "wow":
                post.setWows(
                        post.getWows() + 1
                );
                break;

            case "sad":
                post.setSads(
                        post.getSads() + 1
                );
                break;

            case "angry":
                post.setAngrys(
                        post.getAngrys() + 1
                );
                break;

            default:
                throw new RuntimeException(
                        "Reacción no válida"
                );
        }

        return postRepository.save(post);
    }
}