package com.example.atsopt.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostUpdateDTO(
        @NotBlank(message = "제목을 입력해주세요.")
        @Size(max = 30, message = "제목은 30자 이하로 입력해주세요.")
        String title
) {
}
