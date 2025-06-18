package com.example.atsopt.persistence.repository.post;

import com.example.atsopt.persistence.entity.post.CommentEntity;
import com.example.atsopt.persistence.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByPost(PostEntity postEntity);
}
