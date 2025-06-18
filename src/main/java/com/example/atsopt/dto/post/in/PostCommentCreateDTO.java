package com.example.atsopt.dto.post.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostCommentCreateDTO(
        @NotBlank(message = "댓글 내용을 입력해주세요.")
        @Size(max = 300, message = "댓글은 300자 이하로 입력해주세요.")
        String content) {
}
