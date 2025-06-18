package com.example.atsopt.persistence.repository.post;

import com.example.atsopt.persistence.entity.post.CommentEntity;
import com.example.atsopt.persistence.entity.post.CommentLikeEntity;
import com.example.atsopt.persistence.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentLikeRepository extends JpaRepository<CommentLikeEntity, Long> {
    boolean existsByUserAndComment(UserEntity user, CommentEntity comment);
    void deleteByUserAndComment(UserEntity user, CommentEntity comment);
    List<CommentLikeEntity> findByComment(CommentEntity comment);
}
