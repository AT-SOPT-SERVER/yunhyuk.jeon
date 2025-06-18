package com.example.atsopt.service.post;

import com.example.atsopt.common.exception.BusinessException;
import com.example.atsopt.dto.post.in.PostCommentCreateDTO;
import com.example.atsopt.dto.post.in.PostCreateDTO;
import com.example.atsopt.dto.post.in.PostUpdateDTO;
import com.example.atsopt.dto.post.out.*;
import com.example.atsopt.persistence.entity.post.*;
import com.example.atsopt.persistence.entity.user.UserEntity;
import com.example.atsopt.persistence.repository.post.CommentLikeRepository;
import com.example.atsopt.persistence.repository.post.PostLikeRepository;
import com.example.atsopt.persistence.repository.user.UserRepository;
import com.example.atsopt.persistence.repository.post.CommentRepository;
import com.example.atsopt.persistence.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostLikeRepository postLikeRepository;
    private final CommentLikeRepository commentLikeRepository;

    public void createPost(Long userId, PostCreateDTO postCreateDTO) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("사용자를 찾을 수 없습니다."));

        PostEntity postEntity = PostEntity
                .builder()
                .title(postCreateDTO.title())
                .content(postCreateDTO.content())
                .user(userEntity)
                .postTag(postCreateDTO.postTag())
                .build();

       postRepository.save(postEntity);
    }

    public PostDetailResponseDTO getPostById(Long id) {
        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(() -> new BusinessException("존재하지 않는 게시물입니다."));

        List<CommentEntity> commentEntityList = commentRepository.findAllByPost(postEntity);
        List<CommentResponseDTO> commentResponseDTOList = commentEntityList.stream()
                .map(CommentResponseDTO::from)
                .toList();
        CommentListResponseDTO commentListResponseDTO = CommentListResponseDTO.of(commentResponseDTOList);

        return PostDetailResponseDTO.from(postEntity, commentListResponseDTO);
    }

    public PostListResponseDTO getAllPosts() {
        List<PostEntity> postEntityList = postRepository.findAll();
        List<PostResponseDTO> postResponseDTOList = postEntityList.stream()
                .map(PostResponseDTO::from)
                .toList();
        return PostListResponseDTO.of(postResponseDTOList);
    }

    public void deletePost(Long id) {
        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(() -> new BusinessException("존재하지 않는 게시물입니다."));
        postRepository.delete(postEntity);
    }

    public void updatePost(Long id, PostUpdateDTO postUpdateDTO) {
        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(() -> new BusinessException("존재하지 않는 게시물입니다."));
        postEntity.update(postUpdateDTO.title(), postUpdateDTO.content(), postUpdateDTO.postTag());
    }

    public PostSearchResponse searchPosts(String keyword) {
        // 게시글 제목 기반 조회 -> post 테이블에 title full text index 설정
        List<PostEntity> postEntityList1 = postRepository.searchByTitle(keyword);
        List<PostResponseDTO> postResponseDTOList1 = postEntityList1.stream()
                .map(PostResponseDTO::from)
                .toList();
        PostTitleSearchResponse postTitleSearchResponse = PostTitleSearchResponse.of(postResponseDTOList1);

        // 작성자 이름 기반 조회 -> user 테이블에 name index 설정
        UserEntity userEntity = userRepository.findByName(keyword);
        List<PostEntity> postEntityList2 = postRepository.findAllByUser(userEntity);
        List<PostResponseDTO> postResponseDTOList2 = postEntityList2.stream()
                .map(PostResponseDTO::from)
                .toList();
        PostUserSearchResponse postUserSearchResponse = PostUserSearchResponse.of(postResponseDTOList2);

        return PostSearchResponse.of(postTitleSearchResponse, postUserSearchResponse);
    }

    public PostListResponseDTO searchPostsByTag(PostTag postTag) {
        List<PostEntity> postEntityList = postRepository.findAllByPostTag(postTag);
        List<PostResponseDTO> postResponseDTOList = postEntityList.stream()
                .map(PostResponseDTO::from)
                .toList();
        return PostListResponseDTO.of(postResponseDTOList);
    }

    public void createComment(Long userId, Long id, PostCommentCreateDTO postCommentCreateDTO) {
        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(() -> new BusinessException("게시물을 찾을 수 없습니다."));

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("사용자를 찾을 수 없습니다."));

        CommentEntity commentEntity = CommentEntity.builder()
                .content(postCommentCreateDTO.content())
                .post(postEntity)
                .user(userEntity)
                .build();

        commentRepository.save(commentEntity);
    }

    public void likePost(Long userId, Long postId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("사용자를 찾을 수 없습니다."));
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException("게시물을 찾을 수 없습니다."));

        if (postLikeRepository.existsByUserAndPost(user, post)) {
            throw new BusinessException("이미 좋아요를 눌렀습니다.");
        }

        postLikeRepository.save(PostLikeEntity.builder().user(user).post(post).build());
        post.incrementLikeCount(); // post.likeCount++
    }

    public void unlikePost(Long userId, Long postId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("사용자를 찾을 수 없습니다."));
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException("게시물을 찾을 수 없습니다."));

        if (!postLikeRepository.existsByUserAndPost(user, post)) {
            throw new BusinessException("좋아요를 누르지 않았습니다.");
        }

        postLikeRepository.deleteByUserAndPost(user, post);
        post.decrementLikeCount(); // post.likeCount--
    }

    public List<UserEntity> getPostLikers(Long postId) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException("게시물을 찾을 수 없습니다."));
        return postLikeRepository.findByPost(post)
                .stream()
                .map(PostLikeEntity::getUser)
                .toList();
    }

    public void likeComment(Long userId, Long commentId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("사용자를 찾을 수 없습니다."));
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BusinessException("댓글을 찾을 수 없습니다."));

        if (commentLikeRepository.existsByUserAndComment(user, comment)) {
            throw new BusinessException("이미 좋아요를 눌렀습니다.");
        }

        commentLikeRepository.save(CommentLikeEntity.builder().user(user).comment(comment).build());
        comment.incrementLikeCount();
    }

    public void unlikeComment(Long userId, Long commentId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("사용자를 찾을 수 없습니다."));
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BusinessException("댓글을 찾을 수 없습니다."));

        if (!commentLikeRepository.existsByUserAndComment(user, comment)) {
            throw new BusinessException("좋아요를 누르지 않았습니다.");
        }

        commentLikeRepository.deleteByUserAndComment(user, comment);
        comment.decrementLikeCount();
    }

    public List<UserEntity> getCommentLikers(Long commentId) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BusinessException("댓글을 찾을 수 없습니다."));
        return commentLikeRepository.findByComment(comment)
                .stream()
                .map(CommentLikeEntity::getUser)
                .toList();
    }
}
