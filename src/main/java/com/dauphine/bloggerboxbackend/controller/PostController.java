package com.dauphine.bloggerboxbackend.controller;

import com.dauphine.bloggerboxbackend.dto.CreatePostRequest;
import com.dauphine.bloggerboxbackend.dto.UpdatePostRequest;
import com.dauphine.bloggerboxbackend.model.Post;
import com.dauphine.bloggerboxbackend.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/posts")
@Tag(name = "Posts", description = "Post management endpoints")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    @Operation(summary = "Get all posts")
    public List<Post> getAll() {
        return postService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a post by id")
    public Post getById(@PathVariable UUID id) {
        return postService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new post")
    public Post create(@RequestBody CreatePostRequest request) {
        return postService.create(request.getTitle(), request.getContent(), request.getCategoryId());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a post")
    public Post update(@PathVariable UUID id, @RequestBody UpdatePostRequest request) {
        return postService.update(id, request.getTitle(), request.getContent(), request.getCategoryId());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a post")
    public void delete(@PathVariable UUID id) {
        postService.delete(id);
    }
}
