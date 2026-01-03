package com.java.learning.solid.srp.solution;

import java.util.List;
import java.util.Optional;

/**
 * Ответственность: хранение и поиск пользователей.
 * 
 * TODO: Реализуй этот класс
 */
public class UserRepository {
    
    // TODO: Добавь поле для хранения пользователей (List или Map)
    
    /**
     * Сохраняет пользователя.
     * 
     * @param user пользователь для сохранения
     * @return сохранённый пользователь с назначенным ID
     */
    public User save(User user) {
        // TODO: Реализуй сохранение
        // - Если user.id() == null, назначь новый ID
        // - Добавь в хранилище
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Ищет пользователя по email.
     */
    public Optional<User> findByEmail(String email) {
        // TODO: Реализуй поиск
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Возвращает всех пользователей.
     */
    public List<User> findAll() {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Проверяет, существует ли пользователь с таким email.
     */
    public boolean existsByEmail(String email) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
}

