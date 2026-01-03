package com.java.learning.solid.srp.solution;

/**
 * Ответственность: валидация данных пользователя.
 * 
 * TODO: Реализуй этот класс
 */
public class UserValidator {
    
    /**
     * Проверяет корректность данных для регистрации.
     * 
     * @param name имя пользователя
     * @param email email пользователя
     * @return результат валидации
     */
    public ValidationResult validate(String name, String email) {
        // TODO: Реализуй валидацию
        // - Имя не должно быть null или пустым
        // - Email должен соответствовать формату
        // - Вернуть ValidationResult с ошибками (если есть)
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Результат валидации.
     */
    public record ValidationResult(boolean valid, String errorMessage) {
        public static ValidationResult success() {
            return new ValidationResult(true, null);
        }
        
        public static ValidationResult error(String message) {
            return new ValidationResult(false, message);
        }
    }
}

