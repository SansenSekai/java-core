package com.java.learning.inheritance.notification;

/**
 * Интерфейс для отправки уведомлений.
 * 
 * Демонстрирует default-методы в интерфейсах.
 * 
 * TODO: Реализуй default-методы
 */
public interface Notifier {
    
    /**
     * Отправляет уведомление.
     * 
     * @param message текст сообщения
     * @param recipient получатель
     * @return true, если отправка успешна
     */
    boolean send(String message, String recipient);
    
    /**
     * Возвращает тип уведомителя.
     */
    String getType();
    
    /**
     * Отправляет уведомление с логированием.
     * 
     * Default-метод, который:
     * 1. Логирует попытку отправки
     * 2. Вызывает send()
     * 3. Логирует результат
     * 
     * @param message текст сообщения
     * @param recipient получатель
     * @return true, если отправка успешна
     */
    default boolean sendWithLogging(String message, String recipient) {
        // TODO: Реализуй default-метод
        // 1. Выведи в консоль: "[TYPE] Sending to RECIPIENT..."
        // 2. Вызови send()
        // 3. Выведи результат: "[TYPE] Result: SUCCESS/FAILED"
        // 4. Верни результат send()
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Проверяет, доступен ли сервис отправки.
     * 
     * По умолчанию всегда возвращает true.
     * Реализации могут переопределить для проверки доступности сервиса.
     */
    default boolean isAvailable() {
        return true;
    }
    
    /**
     * Отправляет уведомление нескольким получателям.
     * 
     * @param message текст сообщения
     * @param recipients получатели
     * @return количество успешных отправок
     */
    default int sendToMultiple(String message, String... recipients) {
        // TODO: Реализуй default-метод
        // Вызови send() для каждого получателя и посчитай успешные отправки
        throw new UnsupportedOperationException("TODO: implement");
    }
}

