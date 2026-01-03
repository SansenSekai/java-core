package com.java.learning.inheritance.notification;

/**
 * Отправитель email-уведомлений.
 * 
 * TODO: Реализуй этот класс
 */
public class EmailNotifier implements Notifier {
    
    // TODO: Добавь поле для хранения формата (HTML или PLAIN_TEXT)
    
    public enum Format {
        HTML,
        PLAIN_TEXT
    }
    
    public EmailNotifier() {
        this(Format.PLAIN_TEXT);
    }
    
    public EmailNotifier(Format format) {
        // TODO: Реализуй конструктор
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public boolean send(String message, String recipient) {
        // TODO: Реализуй отправку email
        // 1. Валидируй email получателя (простая проверка на @)
        // 2. Если HTML формат — оберни сообщение в <html><body>...</body></html>
        // 3. Симулируй отправку (просто верни true для валидного email)
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public String getType() {
        return "EMAIL";
    }
    
    public Format getFormat() {
        // TODO: Реализуй геттер
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Форматирует сообщение согласно текущему формату.
     */
    public String formatMessage(String message) {
        // TODO: Реализуй форматирование
        // Для HTML: <html><body>message</body></html>
        // Для PLAIN_TEXT: вернуть как есть
        throw new UnsupportedOperationException("TODO: implement");
    }
}

