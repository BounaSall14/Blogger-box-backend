package com.dauphine.bloggerboxbackend.controller;

import com.dauphine.bloggerboxbackend.dto.CreateCategoryRequest;
import com.dauphine.bloggerboxbackend.model.Category;
import com.dauphine.bloggerboxbackend.model.Post;
import com.dauphine.bloggerboxbackend.service.CategoryService;
import com.dauphine.bloggerboxbackend.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Operation(summary = "Get all categories")
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a category by id")
    public Category getById(@PathVariable UUID id) {
        return categoryService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new category")
    public Category create(@RequestBody CreateCategoryRequest request) {
        return categoryService.create(request.getName());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a category name")
    public Category update(@PathVariable UUID id, @RequestBody CreateCategoryRequest request) {
        return categoryService.update(id, request.getName());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category")
    public void delete(@PathVariable UUID id) {
        categoryService.delete(id);
    }

    @GetMapping("/{id}/posts")
    @Operation(summary = "Get all posts of a category")
    public List<Post> getPostsByCategory(@PathVariable UUID id) {
        return postService.getByCategoryId(id);
    }
}
