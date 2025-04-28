package com.example.atsopt.dto.post.out;

import com.example.atsopt.dto.user.out.UserResponseDTO;
import com.example.atsopt.repository.post.PostEntity;

public record PostResponseDTO(Long id, String title, UserResponseDTO userResponseDTO) {
    public static PostResponseDTO from(PostEntity postEntity) {
        return new PostResponseDTO(
                postEntity.getId(),
                postEntity.getTitle(),
                UserResponseDTO.from(postEntity.getUser()));
    }
}
