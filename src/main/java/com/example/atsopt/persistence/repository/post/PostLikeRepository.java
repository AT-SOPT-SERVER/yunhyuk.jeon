package com.example.atsopt.persistence.repository.post;

import com.example.atsopt.persistence.entity.post.PostEntity;
import com.example.atsopt.persistence.entity.post.PostLikeEntity;
import com.example.atsopt.persistence.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostLikeRepository extends JpaRepository<PostLikeEntity, Long> {
    boolean existsByUserAndPost(UserEntity user, PostEntity post);
    void deleteByUserAndPost(UserEntity user, PostEntity post);
    List<PostLikeEntity> findByPost(PostEntity post);
}
