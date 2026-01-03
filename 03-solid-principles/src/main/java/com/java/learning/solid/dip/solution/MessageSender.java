package com.java.learning.solid.dip.solution;

/**
 * Абстракция для отправки сообщений.
 * 
 * Высокоуровневые модули зависят от этой абстракции,
 * а не от конкретных реализаций.
 */
public interface MessageSender {
    
    /**
     * Отправляет сообщение.
     * 
     * @param recipient получатель
     * @param subject тема
     * @param body тело сообщения
     * @return true, если отправка успешна
     */
    boolean send(String recipient, String subject, String body);
    
    /**
     * Проверяет доступность сервиса отправки.
     */
    default boolean isAvailable() {
        return true;
    }
}

