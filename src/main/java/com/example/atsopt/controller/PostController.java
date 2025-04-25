package com.example.atsopt.controller;

import com.example.atsopt.dto.in.PostCreateDTO;
import com.example.atsopt.dto.in.PostUpdateDTO;
import com.example.atsopt.dto.out.PostListResponseDTO;
import com.example.atsopt.dto.out.PostResponseDTO;
import com.example.atsopt.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public void createPost(@RequestBody @Valid PostCreateDTO postCreateDTO) {
        postService.createPost(postCreateDTO);
    }

    @GetMapping("/post")
    public ResponseEntity<PostListResponseDTO> getAllPosts() {
        PostListResponseDTO postListResponseDTO = postService.getAllPosts();
        return ResponseEntity.ok(postListResponseDTO);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Long id) {
        PostResponseDTO postResponseDTO = postService.getPostById(id);
        return ResponseEntity.ok(postResponseDTO);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/post/{id}")
    public ResponseEntity<Void> updatePost(@PathVariable Long id, @RequestBody @Valid PostUpdateDTO postUpdateDTO) {
        postService.updatePost(id, postUpdateDTO);
        return ResponseEntity.noContent().build();
    }
}
