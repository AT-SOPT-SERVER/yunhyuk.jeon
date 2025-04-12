package com.example.atsopt.service;

import com.example.atsopt.dto.in.PostCreateDTO;
import com.example.atsopt.dto.out.PostResponseDTO;
import com.example.atsopt.repository.PostEntity;
import com.example.atsopt.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void createPost(PostCreateDTO postCreateDTO) {
        PostEntity postEntity = PostEntity
                .builder()
                .name(postCreateDTO.title())
                .build();
       postRepository.save(postEntity);
    }

    public PostResponseDTO getPostById(Long id) {
        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return new PostResponseDTO(postEntity.getId(), postEntity.getName());
    }
}
