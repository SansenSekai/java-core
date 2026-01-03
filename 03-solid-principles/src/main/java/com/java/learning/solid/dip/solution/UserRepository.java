package com.java.learning.solid.dip.solution;

import java.util.Optional;

/**
 * Абстракция для получения данных пользователя.
 */
public interface UserRepository {
    
    /**
     * Находит email пользователя по ID.
     */
    Optional<String> findEmailById(String userId);
}

