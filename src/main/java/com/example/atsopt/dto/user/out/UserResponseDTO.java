package com.example.atsopt.dto.user.out;

import com.example.atsopt.repository.user.UserEntity;

public record UserResponseDTO(Long id, String name) {
    public static UserResponseDTO from(UserEntity userEntity) {
        return new UserResponseDTO(userEntity.getId(), userEntity.getName());
    }
}
