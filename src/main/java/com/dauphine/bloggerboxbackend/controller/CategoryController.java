package com.dauphine.bloggerboxbackend.controller;

import com.dauphine.bloggerboxbackend.dto.CreateCategoryRequest;
import com.dauphine.bloggerboxbackend.model.Category;
import com.dauphine.bloggerboxbackend.model.Post;
import com.dauphine.bloggerboxbackend.service.CategoryService;
import com.dauphine.bloggerboxbackend.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categories")
@Tag(name = "Categories", description = "Category management endpoints")
public class CategoryController {

    private final CategoryService categoryService;
    private final PostService postService;

    public CategoryController(CategoryService categoryService, PostService postService) {
        this.categoryService = categoryService;
        this.postService = postService;
    }

    @GetMapping
    @Operation(summary = "Get all categories, optionally filtered by name")
    public ResponseEntity<List<Category>> getAll(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(categoryService.getAll(name));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a category by id")
    public ResponseEntity<Category> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new category")
    public ResponseEntity<Category> create(@RequestBody CreateCategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(request.getName()));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a category name")
    public ResponseEntity<Category> update(@PathVariable UUID id, @RequestBody CreateCategoryRequest request) {
        return ResponseEntity.ok(categoryService.update(id, request.getName()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/posts")
    @Operation(summary = "Get all posts of a category")
    public ResponseEntity<List<Post>> getPostsByCategory(@PathVariable UUID id) {
        return ResponseEntity.ok(postService.getByCategoryId(id));
    }
}
