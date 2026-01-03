package com.java.learning.solid.srp.solution;

/**
 * Ответственность: отправка email-уведомлений.
 * 
 * TODO: Реализуй этот класс
 */
public class EmailNotifier {
    
    /**
     * Отправляет приветственное письмо новому пользователю.
     * 
     * @param user зарегистрированный пользователь
     */
    public void sendWelcomeEmail(User user) {
        // TODO: Реализуй отправку приветственного письма
        // (эмуляция — просто выведи в консоль)
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Отправляет произвольное сообщение.
     * 
     * @param to адрес получателя
     * @param subject тема письма
     * @param body тело письма
     */
    public void send(String to, String subject, String body) {
        // TODO: Реализуй отправку
        throw new UnsupportedOperationException("TODO: implement");
    }
}

