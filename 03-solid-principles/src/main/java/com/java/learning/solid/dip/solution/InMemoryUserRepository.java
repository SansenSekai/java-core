package com.java.learning.solid.dip.solution;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * In-memory реализация UserRepository для тестирования.
 * 
 * TODO: Реализуй этот класс
 */
public class InMemoryUserRepository implements UserRepository {
    
    private final Map<String, String> userEmails = new HashMap<>();
    
    /**
     * Добавляет пользователя в репозиторий.
     */
    public void addUser(String userId, String email) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public Optional<String> findEmailById(String userId) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
}

