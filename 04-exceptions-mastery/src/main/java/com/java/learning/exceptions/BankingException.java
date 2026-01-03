package com.java.learning.exceptions;

/**
 * Базовое исключение для банковских операций.
 * 
 * TODO: Реализуй это исключение
 * 
 * Вопрос для размышления: должно ли это быть checked или unchecked?
 * Подсказка: подумай, может ли вызывающий код восстановиться после ошибки.
 */
public class BankingException extends RuntimeException {
    
    /**
     * Создаёт исключение с сообщением.
     */
    public BankingException(String message) {
        super(message);
        // TODO: Реализуй конструктор правильно
    }
    
    /**
     * Создаёт исключение с сообщением и причиной.
     * 
     * Важно для сохранения stack trace при re-throw!
     */
    public BankingException(String message, Throwable cause) {
        super(message, cause);
        // TODO: Реализуй конструктор правильно
    }
}
