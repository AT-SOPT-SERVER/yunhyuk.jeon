package com.example.atsopt.service;

import com.example.atsopt.repository.TestEntity;
import com.example.atsopt.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    public void save(String name) {
        TestEntity testEntity = TestEntity.builder()
                .name(name)
                .build();
        testRepository.save(testEntity);
    }

}
