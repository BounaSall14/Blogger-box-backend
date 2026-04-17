package com.dauphine.bloggerboxbackend.service;

import com.dauphine.bloggerboxbackend.model.Category;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {

    private final List<Category> categories = new ArrayList<>(List.of(
        new Category(UUID.fromString("a1b2c3d4-e5f6-7890-abcd-ef1234567890"), "Technology"),
        new Category(UUID.fromString("b2c3d4e5-f6a7-8901-bcde-f12345678901"), "Science"),
        new Category(UUID.fromString("c3d4e5f6-a7b8-9012-cdef-123456789012"), "Travel"),
        new Category(UUID.fromString("d4e5f6a7-b8c9-0123-def0-234567890123"), "Food")
    ));

    public List<Category> getAll() {
        return categories;
    }

    public Category getById(UUID id) {
        return categories.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Category create(String name) {
        Category category = new Category(UUID.randomUUID(), name);
        categories.add(category);
        return category;
    }

    public Category update(UUID id, String name) {
        Category category = getById(id);
        if (category != null) {
            category.setName(name);
        }
        return category;
    }

    public boolean delete(UUID id) {
        return categories.removeIf(c -> c.getId().equals(id));
    }
}
