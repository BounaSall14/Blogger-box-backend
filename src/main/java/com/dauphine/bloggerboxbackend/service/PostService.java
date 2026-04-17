package com.dauphine.bloggerboxbackend.service;

import com.dauphine.bloggerboxbackend.exception.EntityNotFoundException;
import com.dauphine.bloggerboxbackend.model.Category;
import com.dauphine.bloggerboxbackend.model.Post;
import com.dauphine.bloggerboxbackend.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final CategoryService categoryService;

    public PostService(PostRepository postRepository, CategoryService categoryService) {
        this.postRepository = postRepository;
        this.categoryService = categoryService;
    }

    public List<Post> getAll(String value) {
        if (value != null && !value.isBlank()) {
            return postRepository.search(value);
        }
        return postRepository.findAll();
    }

    public List<Post> getByCategoryId(UUID categoryId) {
        return postRepository.findByCategoryId(categoryId);
    }

    public Post getById(UUID id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post", id));
    }

    public Post create(String title, String content, UUID categoryId) {
        Category category = categoryService.getById(categoryId);
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCreatedDate(LocalDateTime.now());
        post.setCategory(category);
        return postRepository.save(post);
    }

    public Post update(UUID id, String title, String content, UUID categoryId) {
        Post post = getById(id);
        post.setTitle(title);
        post.setContent(content);
        post.setCategory(categoryService.getById(categoryId));
        return postRepository.save(post);
    }

    public void delete(UUID id) {
        if (!postRepository.existsById(id)) {
            throw new EntityNotFoundException("Post", id);
        }
        postRepository.deleteById(id);
    }
}
