package com.dauphine.bloggerboxbackend.service;

import com.dauphine.bloggerboxbackend.exception.EntityNotFoundException;
import com.dauphine.bloggerboxbackend.model.Category;
import com.dauphine.bloggerboxbackend.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll(String name) {
        if (name != null && !name.isBlank()) {
            return categoryRepository.findByNameContainingIgnoreCase(name);
        }
        return categoryRepository.findAll();
    }

    public Category getById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category", id));
    }

    public Category create(String name) {
        Category category = new Category();
        category.setName(name);
        return categoryRepository.save(category);
    }

    public Category update(UUID id, String name) {
        Category category = getById(id);
        category.setName(name);
        return categoryRepository.save(category);
    }

    public void delete(UUID id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category", id);
        }
        categoryRepository.deleteById(id);
    }
}
