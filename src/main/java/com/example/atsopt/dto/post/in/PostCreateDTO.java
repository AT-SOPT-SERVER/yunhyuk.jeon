package com.example.atsopt.dto.post.in;

import com.example.atsopt.repository.post.PostTag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostCreateDTO(
        @NotBlank(message = "제목을 입력해주세요.")
        @Size(max = 30, message = "제목은 30자 이하로 입력해주세요.")
        String title,
        @NotBlank(message = "내용을 입력해주세요.")
        @Size(max = 1000, message = "제목은 1000자 이하로 입력해주세요.")
        String content,
        PostTag postTag
) {
}
