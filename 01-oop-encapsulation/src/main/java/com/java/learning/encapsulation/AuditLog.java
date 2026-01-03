package com.java.learning.encapsulation;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Журнал аудита с защитой от модификации.
 * 
 * Особенности:
 * - Записи можно добавлять
 * - Записи нельзя удалять или изменять
 * - Получение списка записей не даёт возможности его модифицировать
 * 
 * TODO: Реализуй этот класс согласно требованиям
 */
public class AuditLog {
    
    // TODO: Определи необходимые поля
    
    public AuditLog() {
        // TODO: Реализуй конструктор
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Добавляет новую запись в журнал.
     * 
     * @param action описание действия (не null, не пустое)
     * @param user пользователь (не null, не пустое)
     * @throws IllegalArgumentException если аргументы некорректны
     */
    public void addEntry(String action, String user) {
        // TODO: Реализуй метод
        // - Проверь аргументы
        // - Создай новую запись с текущим временем
        // - Добавь в список
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Возвращает все записи журнала.
     * 
     * ВАЖНО: Возвращённый список не должен позволять:
     * - Добавлять новые элементы
     * - Удалять существующие
     * - Изменять элементы списка
     * 
     * @return неизменяемый список записей
     */
    public List<AuditEntry> getEntries() {
        // TODO: Реализуй метод
        // Подсказка: List.copyOf()? Collections.unmodifiableList()? 
        // В чём разница?
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Возвращает записи, сделанные указанным пользователем.
     */
    public List<AuditEntry> getEntriesByUser(String user) {
        // TODO: Реализуй метод
        // Подсказка: используй Stream API для фильтрации
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Возвращает записи за указанный период.
     */
    public List<AuditEntry> getEntriesBetween(LocalDateTime from, LocalDateTime to) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Возвращает количество записей в журнале.
     */
    public int size() {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Запись аудита.
     * 
     * Используем record для автоматической immutability.
     */
    public record AuditEntry(
        LocalDateTime timestamp,
        String action,
        String user
    ) {
        // Compact constructor для валидации
        public AuditEntry {
            if (timestamp == null) {
                throw new IllegalArgumentException("Timestamp cannot be null");
            }
            if (action == null || action.isBlank()) {
                throw new IllegalArgumentException("Action cannot be null or empty");
            }
            if (user == null || user.isBlank()) {
                throw new IllegalArgumentException("User cannot be null or empty");
            }
        }
    }
}

