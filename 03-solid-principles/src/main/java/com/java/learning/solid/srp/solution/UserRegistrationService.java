package com.java.learning.solid.srp.solution;

/**
 * Сервис регистрации пользователей.
 * 
 * Единственная ответственность: координация процесса регистрации.
 * Всю реальную работу делегирует специализированным классам.
 * 
 * TODO: Реализуй этот класс
 */
public class UserRegistrationService {
    
    // TODO: Добавь зависимости:
    // - UserValidator
    // - UserRepository
    // - EmailNotifier
    
    public UserRegistrationService(
            UserValidator validator,
            UserRepository repository,
            EmailNotifier notifier) {
        // TODO: Реализуй конструктор
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Регистрирует нового пользователя.
     * 
     * @param name имя пользователя
     * @param email email пользователя
     * @return зарегистрированный пользователь
     * @throws IllegalArgumentException если данные невалидны
     * @throws IllegalStateException если email уже занят
     */
    public User register(String name, String email) {
        // TODO: Реализуй регистрацию
        // 1. Валидация через UserValidator
        // 2. Проверка уникальности email через UserRepository
        // 3. Сохранение через UserRepository
        // 4. Отправка уведомления через EmailNotifier
        // 5. Возврат созданного пользователя
        throw new UnsupportedOperationException("TODO: implement");
    }
}

