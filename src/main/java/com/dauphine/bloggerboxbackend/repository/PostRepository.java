package com.dauphine.bloggerboxbackend.repository;

import com.dauphine.bloggerboxbackend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    List<Post> findByCategoryId(UUID categoryId);

    @Query("SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :value, '%')) OR LOWER(p.content) LIKE LOWER(CONCAT('%', :value, '%'))")
    List<Post> search(@Param("value") String value);
}
