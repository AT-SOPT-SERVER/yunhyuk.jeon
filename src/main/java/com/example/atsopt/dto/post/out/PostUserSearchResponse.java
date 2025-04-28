package com.example.atsopt.dto.post.out;

import java.util.List;

public record PostUserSearchResponse(List<PostResponseDTO> postResponseDTOList) {
    public static PostUserSearchResponse of(List<PostResponseDTO> postResponseDTOList) {
        return new PostUserSearchResponse(postResponseDTOList);
    }
}
