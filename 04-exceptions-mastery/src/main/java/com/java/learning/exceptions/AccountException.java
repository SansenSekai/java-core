package com.java.learning.exceptions;

/**
 * Исключение, связанное с операциями над счётом.
 * 
 * TODO: Реализуй это исключение полностью
 */
public class AccountException extends BankingException {
    
    // TODO: Добавь поле для хранения номера счёта
    private final String accountNumber;
    
    public AccountException(String message, String accountNumber) {
        super(message);
        this.accountNumber = accountNumber;
        // TODO: Включи номер счёта в сообщение более информативно
    }
    
    public AccountException(String message, String accountNumber, Throwable cause) {
        super(message, cause);
        this.accountNumber = accountNumber;
        // TODO: Реализуй конструктор полностью
    }
    
    /**
     * Возвращает номер счёта, с которым связана ошибка.
     */
    public String getAccountNumber() {
        return accountNumber;
        // TODO: Проверь, что это правильная реализация
    }
}
