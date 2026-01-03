package com.java.learning.inheritance.payment;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Представляет платёж в системе.
 */
public record Payment(
    String id,
    BigDecimal amount,
    String currency,
    PaymentMethod method,
    PaymentDetails details
) {
    public Payment {
        Objects.requireNonNull(id, "Payment ID cannot be null");
        Objects.requireNonNull(amount, "Amount cannot be null");
        Objects.requireNonNull(currency, "Currency cannot be null");
        Objects.requireNonNull(method, "Payment method cannot be null");
        Objects.requireNonNull(details, "Payment details cannot be null");
        
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }
}

