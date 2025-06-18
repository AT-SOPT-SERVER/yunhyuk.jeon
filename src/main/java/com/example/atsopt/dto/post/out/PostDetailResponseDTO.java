package com.example.atsopt.dto.post.out;

import com.example.atsopt.dto.user.out.UserResponseDTO;
import com.example.atsopt.persistence.entity.post.PostEntity;
import com.example.atsopt.persistence.entity.post.PostTag;

public record PostDetailResponseDTO(Long id,
                                    String title,
                                    String content,
                                    UserResponseDTO userResponseDTO,
                                    PostTag postTag,
                                    CommentListResponseDTO commentListResponseDTO,
                                    long likeCount) {
    public static PostDetailResponseDTO from(PostEntity postEntity, CommentListResponseDTO commentListResponseDTO) {
        return new PostDetailResponseDTO(
                postEntity.getId(),
                postEntity.getTitle(),
                postEntity.getContent(),
                UserResponseDTO.from(postEntity.getUser()),
                postEntity.getPostTag(),
                commentListResponseDTO,
                postEntity.getLikeCount()
        );
    }
}
