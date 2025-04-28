package com.example.atsopt.repository.post;

import com.example.atsopt.repository.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    @Query(value = "SELECT * FROM post WHERE MATCH(title) AGAINST(:keyword IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<PostEntity> searchByTitle(@Param("keyword") String keyword);
    List<PostEntity> findAllByUser(UserEntity userEntity);
    List<PostEntity> findAllByPostTag(PostTag postTag);
}
