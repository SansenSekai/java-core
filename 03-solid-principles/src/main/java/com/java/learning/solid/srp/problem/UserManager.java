package com.java.learning.solid.srp.problem;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * ПРОБЛЕМА: Этот класс нарушает SRP — у него слишком много ответственностей.
 * 
 * Ответственности:
 * 1. Валидация пользователей
 * 2. Хранение пользователей (эмуляция БД)
 * 3. Отправка уведомлений
 * 4. Генерация отчётов
 * 5. Логирование
 * 
 * При изменении любого из этих аспектов придётся изменять этот класс.
 * 
 * ЗАДАНИЕ: Рефактори этот код в пакете srp.solution,
 * разбив на классы с единственной ответственностью.
 */
public class UserManager {
    
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    
    private final List<User> users = new ArrayList<>();
    private final List<String> logs = new ArrayList<>();
    
    /**
     * Регистрирует нового пользователя.
     */
    public void registerUser(String name, String email) {
        // Логирование
        log("Attempting to register user: " + email);
        
        // Валидация
        if (name == null || name.isBlank()) {
            log("Registration failed: name is empty");
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            log("Registration failed: invalid email " + email);
            throw new IllegalArgumentException("Invalid email: " + email);
        }
        if (findByEmail(email) != null) {
            log("Registration failed: email already exists " + email);
            throw new IllegalArgumentException("Email already registered: " + email);
        }
        
        // Сохранение
        User user = new User(users.size() + 1L, name, email);
        users.add(user);
        log("User registered: " + user.id());
        
        // Отправка уведомления (эмуляция)
        sendWelcomeEmail(user);
    }
    
    /**
     * Находит пользователя по email.
     */
    public User findByEmail(String email) {
        return users.stream()
            .filter(u -> u.email().equals(email))
            .findFirst()
            .orElse(null);
    }
    
    /**
     * Возвращает всех пользователей.
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
    
    /**
     * Отправляет приветственное письмо.
     */
    private void sendWelcomeEmail(User user) {
        // Эмуляция отправки email
        String message = String.format(
            "To: %s\nSubject: Welcome!\n\nHello %s, welcome to our service!",
            user.email(), user.name()
        );
        log("Email sent to " + user.email());
        System.out.println("📧 " + message);
    }
    
    /**
     * Генерирует отчёт по пользователям.
     */
    public String generateUserReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== User Report ===\n");
        report.append("Total users: ").append(users.size()).append("\n\n");
        
        for (User user : users) {
            report.append("- ").append(user.name())
                .append(" (").append(user.email()).append(")\n");
        }
        
        log("Report generated");
        return report.toString();
    }
    
    /**
     * Генерирует отчёт в формате CSV.
     */
    public String generateCsvReport() {
        StringBuilder csv = new StringBuilder();
        csv.append("id,name,email\n");
        
        for (User user : users) {
            csv.append(user.id()).append(",")
                .append(user.name()).append(",")
                .append(user.email()).append("\n");
        }
        
        log("CSV report generated");
        return csv.toString();
    }
    
    /**
     * Логирует сообщение.
     */
    private void log(String message) {
        String logEntry = "[" + java.time.LocalDateTime.now() + "] " + message;
        logs.add(logEntry);
        System.out.println(logEntry);
    }
    
    /**
     * Возвращает логи.
     */
    public List<String> getLogs() {
        return new ArrayList<>(logs);
    }
    
    /**
     * Модель пользователя.
     */
    public record User(Long id, String name, String email) {}
}

