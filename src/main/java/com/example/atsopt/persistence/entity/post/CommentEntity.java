package com.example.atsopt.persistence.entity.post;

import com.example.atsopt.persistence.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    String content;

    @ManyToOne
    PostEntity post;

    @ManyToOne
    UserEntity user;

    long likeCount;

    @Builder
    public CommentEntity(String content, PostEntity post, UserEntity user, long likeCount) {
        this.content = content;
        this.post = post;
        this.user = user;
        this.likeCount = 0L;
    }

    public void incrementLikeCount() {
        this.likeCount++;
    }
    public void decrementLikeCount() {
        this.likeCount--;
    }
}
