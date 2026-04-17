package com.dauphine.bloggerboxbackend.service;

import com.dauphine.bloggerboxbackend.model.Category;
import com.dauphine.bloggerboxbackend.model.Post;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PostService {

    private final CategoryService categoryService;
    private final List<Post> posts = new ArrayList<>();

    public PostService(CategoryService categoryService) {
        this.categoryService = categoryService;
        initMockData();
    }

    private void initMockData() {
        Category tech    = categoryService.getById(UUID.fromString("a1b2c3d4-e5f6-7890-abcd-ef1234567890"));
        Category science = categoryService.getById(UUID.fromString("b2c3d4e5-f6a7-8901-bcde-f12345678901"));
        Category travel  = categoryService.getById(UUID.fromString("c3d4e5f6-a7b8-9012-cdef-123456789012"));
        Category food    = categoryService.getById(UUID.fromString("d4e5f6a7-b8c9-0123-def0-234567890123"));

        posts.add(new Post(UUID.fromString("e5f6a7b8-c9d0-1234-ef01-345678901234"),
                "Introduction to Spring Boot",
                "Spring Boot makes it easy to create stand-alone, production-grade Spring applications.",
                LocalDateTime.of(2024, 1, 15, 10, 30), tech));
        posts.add(new Post(UUID.fromString("f6a7b8c9-d0e1-2345-f012-456789012345"),
                "Getting Started with Angular",
                "Angular is a platform and framework for building single-page client applications.",
                LocalDateTime.of(2024, 1, 20, 14, 0), tech));
        posts.add(new Post(UUID.fromString("a7b8c9d0-e1f2-3456-0123-567890123456"),
                "Exploring the French Riviera",
                "The French Riviera is a beautiful destination along the Mediterranean coast of France.",
                LocalDateTime.of(2024, 2, 1, 9, 0), travel));
        posts.add(new Post(UUID.fromString("b8c9d0e1-f2a3-4567-1234-678901234567"),
                "The Wonders of Space",
                "Space exploration has led to many incredible discoveries about our universe.",
                LocalDateTime.of(2024, 2, 10, 11, 30), science));
        posts.add(new Post(UUID.fromString("c9d0e1f2-a3b4-5678-2345-789012345678"),
                "Best Pasta Recipes",
                "Italian cuisine is famous worldwide for its pasta dishes.",
                LocalDateTime.of(2024, 2, 15, 16, 0), food));
    }

    public List<Post> getAll() {
        return posts;
    }

    public List<Post> getByCategoryId(UUID categoryId) {
        return posts.stream()
                .filter(p -> p.getCategory().getId().equals(categoryId))
                .toList();
    }

    public Post getById(UUID id) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Post create(String title, String content, UUID categoryId) {
        Category category = categoryService.getById(categoryId);
        Post post = new Post(UUID.randomUUID(), title, content, LocalDateTime.now(), category);
        posts.add(post);
        return post;
    }

    public Post update(UUID id, String title, String content, UUID categoryId) {
        Post post = getById(id);
        if (post != null) {
            post.setTitle(title);
            post.setContent(content);
            post.setCategory(categoryService.getById(categoryId));
        }
        return post;
    }

    public boolean delete(UUID id) {
        return posts.removeIf(p -> p.getId().equals(id));
    }
}
