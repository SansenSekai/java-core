package com.java.learning.solid.dip.problem;

/**
 * ПРОБЛЕМА: NotificationService напрямую зависит от конкретных реализаций.
 * 
 * - Нельзя заменить SmtpEmailClient на другую реализацию
 * - Нельзя тестировать без реального SMTP-сервера
 * - При изменении SmtpEmailClient придётся изменять и NotificationService
 */
public class NotificationService {
    
    // Прямая зависимость от конкретного класса
    private final SmtpEmailClient emailClient = new SmtpEmailClient();
    
    public void sendNotification(String userId, String message) {
        // Получаем email пользователя (упрощённо)
        String email = getUserEmail(userId);
        
        // Прямой вызов конкретной реализации
        emailClient.sendEmail(email, "Notification", message);
    }
    
    private String getUserEmail(String userId) {
        // Эмуляция получения email
        return userId + "@example.com";
    }
}

