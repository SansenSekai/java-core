package com.java.learning.equalshash;

import java.math.BigDecimal;

/**
 * Класс, представляющий денежную сумму.
 * 
 * TODO: Реализуй equals() и hashCode() согласно контракту
 */
public final class Money {
    
    private final BigDecimal amount;
    private final String currency;
    
    public Money(BigDecimal amount, String currency) {
        if (amount == null) throw new IllegalArgumentException("Amount cannot be null");
        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("Currency cannot be null or empty");
        }
        this.amount = amount;
        this.currency = currency;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public Money add(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot add different currencies");
        }
        return new Money(this.amount.add(other.amount), this.currency);
    }
    
    @Override
    public boolean equals(Object o) {
        // TODO: Реализуй equals
        // Подсказки:
        // 1. Проверь на null
        // 2. Проверь на тот же тип (getClass() или instanceof?)
        // 3. Сравни все значимые поля
        // 4. Для BigDecimal используй compareTo(), не equals()!
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public int hashCode() {
        // TODO: Реализуй hashCode
        // Подсказка: используй Objects.hash()
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public String toString() {
        return amount + " " + currency;
    }
}

