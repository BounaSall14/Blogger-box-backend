package com.dauphine.bloggerboxbackend.exception;

import java.util.UUID;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityName, UUID id) {
        super(entityName + " with id " + id + " not found");
    }
}
