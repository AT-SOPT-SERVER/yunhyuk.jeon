package com.example.atsopt.dto.post.out;

import java.util.List;

public record PostListResponseDTO(List<PostResponseDTO> postResponseDTOList) {
    public static PostListResponseDTO of(List<PostResponseDTO> postResponseDTOList) {
        return new PostListResponseDTO(postResponseDTOList);
    }
}
