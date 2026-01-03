package com.java.learning.annotations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Validator")
class ValidatorTest {

    // Тестовый класс с валидацией
    static class User {
        @Validate(notNull = true, minLength = 3, maxLength = 50)
        private String username;
        
        @Validate(min = 18, max = 120)
        private int age;
        
        @Validate(pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", 
                  message = "Invalid email format")
        private String email;
        
        public User(String username, int age, String email) {
            this.username = username;
            this.age = age;
            this.email = email;
        }
    }
    
    @Nested
    @DisplayName("Валидация строк")
    class StringValidation {
        
        @Test
        @DisplayName("Проходит валидацию с корректными данными")
        void shouldPassWithValidData() {
            User user = new User("john", 25, "john@example.com");
            
            var result = Validator.validate(user);
            
            assertThat(result.isValid()).isTrue();
            assertThat(result.errors()).isEmpty();
        }
        
        @Test
        @DisplayName("Ошибка при null значении")
        void shouldFailOnNull() {
            User user = new User(null, 25, "john@example.com");
            
            var result = Validator.validate(user);
            
            assertThat(result.isValid()).isFalse();
            assertThat(result.errors())
                .extracting(Validator.ValidationError::fieldName)
                .contains("username");
        }
        
        @Test
        @DisplayName("Ошибка при слишком коротком значении")
        void shouldFailOnTooShort() {
            User user = new User("ab", 25, "john@example.com");
            
            var result = Validator.validate(user);
            
            assertThat(result.isValid()).isFalse();
        }
    }
    
    @Nested
    @DisplayName("Валидация чисел")
    class NumberValidation {
        
        @Test
        @DisplayName("Ошибка при значении меньше min")
        void shouldFailOnTooSmall() {
            User user = new User("john", 10, "john@example.com");
            
            var result = Validator.validate(user);
            
            assertThat(result.isValid()).isFalse();
            assertThat(result.errors())
                .extracting(Validator.ValidationError::fieldName)
                .contains("age");
        }
        
        @Test
        @DisplayName("Ошибка при значении больше max")
        void shouldFailOnTooBig() {
            User user = new User("john", 200, "john@example.com");
            
            var result = Validator.validate(user);
            
            assertThat(result.isValid()).isFalse();
        }
    }
    
    @Nested
    @DisplayName("Валидация паттерна")
    class PatternValidation {
        
        @Test
        @DisplayName("Ошибка при несоответствии паттерну")
        void shouldFailOnInvalidPattern() {
            User user = new User("john", 25, "invalid-email");
            
            var result = Validator.validate(user);
            
            assertThat(result.isValid()).isFalse();
            assertThat(result.errors())
                .extracting(Validator.ValidationError::fieldName)
                .contains("email");
        }
    }
    
    @Nested
    @DisplayName("validateOrThrow")
    class ValidateOrThrow {
        
        @Test
        @DisplayName("Выбрасывает исключение при ошибках")
        void shouldThrowOnInvalid() {
            User user = new User(null, 10, "invalid");
            
            assertThatThrownBy(() -> Validator.validateOrThrow(user))
                .isInstanceOf(Validator.ValidationException.class)
                .satisfies(e -> {
                    var ex = (Validator.ValidationException) e;
                    assertThat(ex.getErrors()).hasSizeGreaterThan(0);
                });
        }
        
        @Test
        @DisplayName("Не выбрасывает при валидных данных")
        void shouldNotThrowOnValid() {
            User user = new User("john", 25, "john@example.com");
            
            assertThatNoException().isThrownBy(() -> Validator.validateOrThrow(user));
        }
    }
}

