package com.java.learning.exceptions;

/**
 * Исключение: счёт заблокирован.
 * 
 * TODO: Реализуй это исключение полностью
 */
public class AccountBlockedException extends AccountException {
    
    // TODO: Добавь поле для причины блокировки
    private final String blockReason;
    
    public AccountBlockedException(String accountNumber, String blockReason) {
        super("Account " + accountNumber + " is blocked: " + blockReason, accountNumber);
        this.blockReason = blockReason;
        // TODO: Улучши сообщение, добавь больше контекста
    }
    
    public String getBlockReason() {
        return blockReason;
        // TODO: Проверь, что это правильная реализация
    }
}
