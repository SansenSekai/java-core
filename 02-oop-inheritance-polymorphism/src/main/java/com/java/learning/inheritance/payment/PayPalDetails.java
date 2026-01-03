package com.java.learning.inheritance.payment;

/**
 * Детали платежа через PayPal.
 */
public record PayPalDetails(
    String email
) implements PaymentDetails {
    
    public PayPalDetails {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
    }
}

