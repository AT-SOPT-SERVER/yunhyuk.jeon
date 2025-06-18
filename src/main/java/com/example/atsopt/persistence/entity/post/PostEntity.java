package com.example.atsopt.persistence.entity.post;

import com.example.atsopt.persistence.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    PostTag postTag;

    private long likeCount;

    @Builder
    public PostEntity(String title, String content, UserEntity user, PostTag postTag) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.postTag = postTag;
        this.likeCount = 0L;
    }

    public void update(String title, String content, PostTag postTag) {
        this.title = title;
        this.content = content;
        this.postTag = postTag;
    }

    public void incrementLikeCount() {
        this.likeCount++;
    }
    public void decrementLikeCount() {
        this.likeCount--;
    }
}
