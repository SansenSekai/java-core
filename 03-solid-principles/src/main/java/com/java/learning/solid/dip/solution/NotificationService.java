package com.java.learning.solid.dip.solution;

/**
 * Сервис уведомлений с инвертированными зависимостями.
 * 
 * Зависит от абстракций (MessageSender, UserRepository),
 * а не от конкретных реализаций.
 * 
 * TODO: Реализуй этот класс
 */
public class NotificationService {
    
    // TODO: Добавь поля для зависимостей (MessageSender, UserRepository)
    
    /**
     * Конструктор с внедрением зависимостей.
     * 
     * Это простейшая форма DI (Dependency Injection) без фреймворков.
     */
    public NotificationService(MessageSender messageSender, UserRepository userRepository) {
        // TODO: Реализуй конструктор
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Отправляет уведомление пользователю.
     * 
     * @param userId ID пользователя
     * @param message текст уведомления
     * @return true, если отправка успешна
     * @throws IllegalArgumentException если пользователь не найден
     */
    public boolean sendNotification(String userId, String message) {
        // TODO: Реализуй метод
        // 1. Найди email пользователя через UserRepository
        // 2. Отправь сообщение через MessageSender
        throw new UnsupportedOperationException("TODO: implement");
    }
}

