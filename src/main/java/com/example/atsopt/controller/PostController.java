package com.example.atsopt.controller;

import com.example.atsopt.dto.in.PostCreateDTO;
import com.example.atsopt.dto.out.PostResponseDTO;
import com.example.atsopt.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public void createPost(@RequestBody PostCreateDTO postCreateDTO) {
        postService.createPost(postCreateDTO);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Long id) {
        PostResponseDTO postResponseDTO = postService.getPostById(id);
        return ResponseEntity.ok(postResponseDTO);
    }
}
