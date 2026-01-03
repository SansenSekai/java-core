package com.java.learning.exceptions;

/**
 * Исключение валидации — для некорректных входных данных.
 * 
 * Это unchecked, потому что невалидные данные — это ошибка в коде,
 * который их передал, а не восстановимая ситуация.
 * 
 * TODO: Реализуй это исключение полностью
 */
public class ValidationException extends BankingException {
    
    // TODO: Добавь поле для имени невалидного поля
    private final String fieldName;
    
    public ValidationException(String message, String fieldName) {
        super(message);
        this.fieldName = fieldName;
        // TODO: Улучши сообщение, включив имя поля
    }
    
    public String getFieldName() {
        return fieldName;
        // TODO: Проверь, что это правильная реализация
    }
}
