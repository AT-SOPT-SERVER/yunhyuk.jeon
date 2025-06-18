package com.example.atsopt.dto.post.out;

import com.example.atsopt.dto.user.out.UserResponseDTO;
import com.example.atsopt.persistence.entity.post.PostEntity;

public record PostResponseDTO(Long id, String title, UserResponseDTO userResponseDTO, long likeCount) {
    public static PostResponseDTO from(PostEntity postEntity) {
        return new PostResponseDTO(
                postEntity.getId(),
                postEntity.getTitle(),
                UserResponseDTO.from(postEntity.getUser()),
                postEntity.getLikeCount());
    }
}
