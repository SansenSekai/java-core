package com.java.learning.annotations;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Процессор аннотаций @Validate.
 * 
 * Проверяет объекты на соответствие правилам валидации,
 * заданным аннотациями на полях.
 * 
 * TODO: Реализуй валидатор
 */
public class Validator {
    
    /**
     * Результат валидации одного поля.
     */
    public record ValidationError(String fieldName, String message) {}
    
    /**
     * Результат валидации объекта.
     */
    public record ValidationResult(List<ValidationError> errors) {
        public boolean isValid() {
            return errors.isEmpty();
        }
    }
    
    /**
     * Валидирует объект по аннотациям @Validate.
     * 
     * @param obj объект для валидации
     * @return результат валидации
     */
    public static ValidationResult validate(Object obj) {
        // TODO: Реализуй
        // 1. Получи все поля класса (включая приватные)
        // 2. Для каждого поля с @Validate проверь правила
        // 3. Собери ошибки в список
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Валидирует объект и выбрасывает исключение при ошибках.
     */
    public static void validateOrThrow(Object obj) throws ValidationException {
        ValidationResult result = validate(obj);
        if (!result.isValid()) {
            throw new ValidationException(result.errors());
        }
    }
    
    /**
     * Исключение валидации.
     */
    public static class ValidationException extends RuntimeException {
        private final List<ValidationError> errors;
        
        public ValidationException(List<ValidationError> errors) {
            super("Validation failed: " + errors);
            this.errors = errors;
        }
        
        public List<ValidationError> getErrors() {
            return errors;
        }
    }
}

