package com.dauphine.bloggerboxbackend.controller;

import com.dauphine.bloggerboxbackend.dto.CreatePostRequest;
import com.dauphine.bloggerboxbackend.dto.UpdatePostRequest;
import com.dauphine.bloggerboxbackend.model.Post;
import com.dauphine.bloggerboxbackend.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Operation(summary = "Get all posts, optionally filtered by title or content")
    public ResponseEntity<List<Post>> getAll(@RequestParam(required = false) String value) {
        return ResponseEntity.ok(postService.getAll(value));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a post by id")
    public ResponseEntity<Post> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(postService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new post")
    public ResponseEntity<Post> create(@RequestBody CreatePostRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.create(request.getTitle(), request.getContent(), request.getCategoryId()));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a post")
    public ResponseEntity<Post> update(@PathVariable UUID id, @RequestBody UpdatePostRequest request) {
        return ResponseEntity.ok(postService.update(id, request.getTitle(), request.getContent(), request.getCategoryId()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a post")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
