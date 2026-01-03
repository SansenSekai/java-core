package com.java.learning.optional;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Паттерны безопасной работы с null.
 * 
 * TODO: Реализуй рефакторинг null-небезопасного кода
 */
public class NullSafetyPatterns {
    
    /**
     * Безопасное извлечение вложенного значения.
     * 
     * Было (плохо):
     * <pre>
     * if (user != null && user.getAddress() != null && user.getAddress().getCity() != null) {
     *     return user.getAddress().getCity().getName();
     * }
     * return null;
     * </pre>
     * 
     * TODO: Перепиши с использованием Optional и flatMap
     */
    public static Optional<String> getCityName(User user) {
        // TODO: Реализуй безопасное извлечение
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Выполнение действия только если значение присутствует.
     * 
     * Было (плохо):
     * <pre>
     * if (email != null && !email.isBlank()) {
     *     sendEmail(email, message);
     * }
     * </pre>
     * 
     * TODO: Перепиши с использованием Optional
     */
    public static void sendEmailIfPresent(String email, String message, Consumer<String> emailSender) {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Преобразование с дефолтным значением.
     * 
     * Было (плохо):
     * <pre>
     * String displayName = username;
     * if (displayName == null || displayName.isBlank()) {
     *     displayName = "Anonymous";
     * } else {
     *     displayName = displayName.trim().toUpperCase();
     * }
     * return displayName;
     * </pre>
     * 
     * TODO: Перепиши с использованием Optional
     */
    public static String getDisplayName(String username) {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    // ===== Вспомогательные классы =====
    
    public record User(String name, Address address) {
        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }
    }
    
    public record Address(String street, City city) {
        public Optional<City> getCity() {
            return Optional.ofNullable(city);
        }
    }
    
    public record City(String name, String country) {
        public Optional<String> getName() {
            return Optional.ofNullable(name);
        }
    }
}

