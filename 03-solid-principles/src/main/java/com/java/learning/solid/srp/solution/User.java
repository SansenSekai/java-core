package com.java.learning.solid.srp.solution;

/**
 * Модель пользователя.
 */
public record User(Long id, String name, String email) {
    
    public User {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
    }
}

