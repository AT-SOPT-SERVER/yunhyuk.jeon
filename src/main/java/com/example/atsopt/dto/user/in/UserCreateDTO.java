package com.example.atsopt.dto.user.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateDTO(
    @NotBlank(message = "사용자 이름을 입력해주세요.")
    @Size(max = 10, message = "이름은 10자 이하로 입력해주세요.")
    String name
) {
}
