package com.example.atsopt.dto.post.out;

import java.util.List;

public record CommentListResponseDTO(List<CommentResponseDTO> commentResponseDTOList) {
    public static CommentListResponseDTO of(List<CommentResponseDTO> commentResponseDTOList) {
        return new CommentListResponseDTO(commentResponseDTOList);
    }
}
