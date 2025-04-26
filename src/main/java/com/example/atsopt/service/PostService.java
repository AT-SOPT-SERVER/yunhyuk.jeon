package com.example.atsopt.service;

import com.example.atsopt.common.exception.BusinessException;
import com.example.atsopt.dto.in.PostCreateDTO;
import com.example.atsopt.dto.in.PostUpdateDTO;
import com.example.atsopt.dto.out.PostListResponseDTO;
import com.example.atsopt.dto.out.PostResponseDTO;
import com.example.atsopt.repository.PostEntity;
import com.example.atsopt.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void createPost(PostCreateDTO postCreateDTO) {
        PostEntity postEntity = PostEntity
                .builder()
                .title(postCreateDTO.title())
                .build();
       postRepository.save(postEntity);
    }

    public PostResponseDTO getPostById(Long id) {
        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(() -> new BusinessException("존재하지 않는 게시물입니다."));
        return new PostResponseDTO(postEntity.getId(), postEntity.getTitle());
    }

    public PostListResponseDTO getAllPosts() {
        List<PostEntity> postEntityList = postRepository.findAll();
        List<PostResponseDTO> postResponseDTOList = postEntityList.stream()
                .map(postEntity -> new PostResponseDTO(postEntity.getId(), postEntity.getTitle()))
                .toList();
        return new PostListResponseDTO(postResponseDTOList);
    }

    public void deletePost(Long id) {
        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(() -> new BusinessException("존재하지 않는 게시물입니다."));
        postRepository.delete(postEntity);
    }

    public void updatePost(Long id, PostUpdateDTO postUpdateDTO) {
        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(() -> new BusinessException("존재하지 않는 게시물입니다."));
        postEntity.update(postUpdateDTO.title());
    }
}
