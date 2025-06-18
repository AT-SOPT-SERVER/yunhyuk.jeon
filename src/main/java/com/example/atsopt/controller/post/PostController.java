package com.example.atsopt.controller.post;

import com.example.atsopt.dto.post.in.PostCommentCreateDTO;
import com.example.atsopt.dto.post.in.PostCreateDTO;
import com.example.atsopt.dto.post.in.PostUpdateDTO;
import com.example.atsopt.dto.post.out.PostListResponseDTO;
import com.example.atsopt.dto.post.out.PostDetailResponseDTO;
import com.example.atsopt.dto.post.out.PostPageResponseDTO;
import com.example.atsopt.dto.post.out.PostSearchResponse;
import com.example.atsopt.persistence.entity.post.PostTag;
import com.example.atsopt.service.post.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public void createPost(
            @RequestHeader Long userId,
            @RequestBody @Valid PostCreateDTO postCreateDTO) {
        postService.createPost(userId, postCreateDTO);
    }

//    @GetMapping
//    public ResponseEntity<PostListResponseDTO> getAllPosts() {
//        PostListResponseDTO postListResponseDTO = postService.getAllPosts();
//        return ResponseEntity.ok(postListResponseDTO);
//    }

    @GetMapping
    public ResponseEntity<PostPageResponseDTO> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PostPageResponseDTO response = postService.getAllPosts(page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDetailResponseDTO> getPostById(@PathVariable Long id) {
        PostDetailResponseDTO postDetailResponseDTO = postService.getPostById(id);
        return ResponseEntity.ok(postDetailResponseDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    @PatchMapping("/{id}")
    public void updatePost(@PathVariable Long id, @RequestBody @Valid PostUpdateDTO postUpdateDTO) {
        postService.updatePost(id, postUpdateDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<PostSearchResponse> searchPosts(@RequestParam String keyword) {
        PostSearchResponse postSearchResponse = postService.searchPosts(keyword);
        return ResponseEntity.ok(postSearchResponse);
    }

    @GetMapping("/search/tag")
    public ResponseEntity<PostListResponseDTO> searchPosts(@RequestParam PostTag postTag) {
        PostListResponseDTO postListResponseDTO = postService.searchPostsByTag(postTag);
        return ResponseEntity.ok(postListResponseDTO);
    }

    @PostMapping("{id}/comment")
    public void createComment(@RequestHeader Long userId,
                              @PathVariable Long id,
                              @RequestBody @Valid PostCommentCreateDTO postCommentCreateDTO) {
        postService.createComment(userId, id, postCommentCreateDTO);
    }

    // ===== 게시글 좋아요 =====

    @PostMapping("/{id}/like")
    public void likePost(@RequestHeader Long userId, @PathVariable Long id) {
        postService.likePost(userId, id);
    }

    @DeleteMapping("/{id}/like")
    public void unlikePost(@RequestHeader Long userId, @PathVariable Long id) {
        postService.unlikePost(userId, id);
    }

    @GetMapping("/{id}/like")
    public ResponseEntity<?> getPostLikers(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostLikers(id));
    }

    // ===== 댓글 좋아요 =====

    @PostMapping("/comment/{id}/like")
    public void likeComment(@RequestHeader Long userId, @PathVariable Long id) {
        postService.likeComment(userId, id);
    }

    @DeleteMapping("/comment/{id}/like")
    public void unlikeComment(@RequestHeader Long userId, @PathVariable Long id) {
        postService.unlikeComment(userId, id);
    }

    @GetMapping("/comment/{id}/like")
    public ResponseEntity<?> getCommentLikers(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getCommentLikers(id));
    }
}
