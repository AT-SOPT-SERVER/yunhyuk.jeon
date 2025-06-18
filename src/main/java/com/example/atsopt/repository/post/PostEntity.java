package com.example.atsopt.repository.post;

import com.example.atsopt.repository.user.UserEntity;
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

    @Builder
    public PostEntity(String title, String content, UserEntity user, PostTag postTag) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.postTag = postTag;
    }

    public void update(String title, String content, PostTag postTag) {
        this.title = title;
        this.content = content;
        this.postTag = postTag;
    }
}
