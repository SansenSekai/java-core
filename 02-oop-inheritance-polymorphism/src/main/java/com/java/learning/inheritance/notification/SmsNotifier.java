package com.java.learning.inheritance.notification;

/**
 * Отправитель SMS-уведомлений.
 * 
 * TODO: Реализуй этот класс
 */
public class SmsNotifier implements Notifier {
    
    public static final int MAX_SMS_LENGTH = 160;
    
    @Override
    public boolean send(String message, String recipient) {
        // TODO: Реализуй отправку SMS
        // 1. Валидируй номер телефона (только цифры, длина 10-15)
        // 2. Обрежь сообщение до MAX_SMS_LENGTH символов
        // 3. Симулируй отправку (верни true для валидного номера)
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public String getType() {
        return "SMS";
    }
    
    /**
     * Обрезает сообщение до максимальной длины SMS.
     * 
     * @param message исходное сообщение
     * @return обрезанное сообщение (max 160 символов)
     */
    public String truncateMessage(String message) {
        // TODO: Реализуй обрезку сообщения
        // Если сообщение длиннее MAX_SMS_LENGTH, обрежь и добавь "..."
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Проверяет валидность номера телефона.
     */
    public boolean isValidPhoneNumber(String phoneNumber) {
        // TODO: Реализуй валидацию
        // Удали все нецифровые символы, проверь длину 10-15
        throw new UnsupportedOperationException("TODO: implement");
    }
}

