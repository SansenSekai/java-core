package com.java.learning.exceptions;

import java.math.BigDecimal;

/**
 * Исключение: некорректная сумма.
 * 
 * TODO: Реализуй это исключение полностью
 */
public class InvalidAmountException extends ValidationException {
    
    // TODO: Добавь поле для некорректной суммы
    private final BigDecimal amount;
    
    public InvalidAmountException(BigDecimal amount) {
        super("Invalid amount: " + amount + ". Amount must be positive.", "amount");
        this.amount = amount;
        // TODO: Добавь более детальную валидацию (null, отрицательное, ноль)
    }
    
    public BigDecimal getAmount() {
        return amount;
        // TODO: Проверь, что это правильная реализация
    }
}
