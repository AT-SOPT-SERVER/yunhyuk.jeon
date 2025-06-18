package com.example.atsopt.dto.post.out;

import java.util.List;

public record PostPageResponseDTO(
        List<PostResponseDTO> posts,
        int totalPages,
        int currentPage
) {
    public static PostPageResponseDTO of(List<PostResponseDTO> posts, int totalPages, int currentPage) {
        return new PostPageResponseDTO(posts, totalPages, currentPage);
    }
}
