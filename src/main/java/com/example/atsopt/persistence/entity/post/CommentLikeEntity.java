package com.example.atsopt.persistence.entity.post;

import com.example.atsopt.persistence.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment_like", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "comment_id"})
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    private CommentEntity comment;

    @Builder
    public CommentLikeEntity(UserEntity user, CommentEntity comment) {
        this.user = user;
        this.comment = comment;
    }
}
