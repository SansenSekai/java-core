package com.java.learning.inheritance.notification;

/**
 * Отправитель push-уведомлений.
 * 
 * TODO: Реализуй этот класс
 */
public class PushNotifier implements Notifier {
    
    public static final int MAX_TITLE_LENGTH = 50;
    public static final int MAX_BODY_LENGTH = 200;
    
    @Override
    public boolean send(String message, String recipient) {
        // TODO: Реализуй отправку push-уведомления
        // recipient — это device token (непустая строка длиной > 10)
        // message отправляется как body, title = первые 50 символов
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public String getType() {
        return "PUSH";
    }
    
    /**
     * Отправляет push-уведомление с заголовком и телом.
     * 
     * @param title заголовок (обрезается до MAX_TITLE_LENGTH)
     * @param body тело сообщения (обрезается до MAX_BODY_LENGTH)
     * @param deviceToken токен устройства
     * @return true, если отправка успешна
     */
    public boolean sendWithTitle(String title, String body, String deviceToken) {
        // TODO: Реализуй отправку с отдельным заголовком
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Проверяет валидность device token.
     */
    public boolean isValidDeviceToken(String token) {
        // TODO: Реализуй валидацию
        // Token должен быть непустым и длиннее 10 символов
        throw new UnsupportedOperationException("TODO: implement");
    }
}

