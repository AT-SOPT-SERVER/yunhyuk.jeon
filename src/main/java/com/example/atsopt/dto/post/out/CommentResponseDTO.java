package com.example.atsopt.dto.post.out;

import com.example.atsopt.dto.user.out.UserResponseDTO;
import com.example.atsopt.repository.post.CommentEntity;

public record CommentResponseDTO(String comment, UserResponseDTO user) {
    public static CommentResponseDTO from(CommentEntity commentEntity) {
        return new CommentResponseDTO(commentEntity.getContent(), UserResponseDTO.from(commentEntity.getUser()));
    }
}
