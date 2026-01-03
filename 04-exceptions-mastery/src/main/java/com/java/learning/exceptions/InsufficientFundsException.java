package com.java.learning.exceptions;

import java.math.BigDecimal;

/**
 * Исключение: недостаточно средств на счёте.
 * 
 * TODO: Реализуй это исключение полностью
 */
public class InsufficientFundsException extends AccountException {
    
    // TODO: Добавь поля для запрошенной суммы и доступного баланса
    private final BigDecimal requested;
    private final BigDecimal available;
    
    public InsufficientFundsException(
            String accountNumber, 
            BigDecimal requested, 
            BigDecimal available) {
        super("Insufficient funds on account " + accountNumber + 
              ": requested " + requested + ", available " + available, accountNumber);
        this.requested = requested;
        this.available = available;
        // TODO: Добавь валидацию параметров
    }
    
    public BigDecimal getRequested() {
        return requested;
        // TODO: Проверь, что это правильная реализация
    }
    
    public BigDecimal getAvailable() {
        return available;
        // TODO: Проверь, что это правильная реализация
    }
    
    /**
     * Возвращает недостающую сумму.
     */
    public BigDecimal getShortfall() {
        return requested.subtract(available);
        // TODO: Проверь, что это правильная реализация
    }
}
