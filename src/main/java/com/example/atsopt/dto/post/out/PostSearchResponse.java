package com.example.atsopt.dto.post.out;

public record PostSearchResponse(PostTitleSearchResponse postTitleSearchResponse,
                                 PostUserSearchResponse postUserSearchResponse) {
    public static PostSearchResponse of(PostTitleSearchResponse postTitleSearchResponse,
                                        PostUserSearchResponse postUserSearchResponse) {
        return new PostSearchResponse(postTitleSearchResponse, postUserSearchResponse);
    }
}
