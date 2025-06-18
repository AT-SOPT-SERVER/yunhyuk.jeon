package com.example.atsopt.service.user;

import com.example.atsopt.dto.user.in.UserCreateDTO;
import com.example.atsopt.dto.user.out.UserResponseDTO;
import com.example.atsopt.repository.user.UserEntity;
import com.example.atsopt.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createUser(UserCreateDTO userCreateDTO) {
        UserEntity userEntity = UserEntity.builder()
                .name(userCreateDTO.name())
                .build();
        userRepository.save(userEntity);
    }
}
