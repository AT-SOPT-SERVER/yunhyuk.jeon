package com.example.atsopt.dto.post.out;

import java.util.List;

public record PostTitleSearchResponse(List<PostResponseDTO> postResponseDTOList) {
    public static PostTitleSearchResponse of(List<PostResponseDTO> postResponseDTOList) {
        return new PostTitleSearchResponse(postResponseDTOList);
    }
}
