package com.java.learning.inheritance.payment;

/**
 * Детали платежа кредитной картой.
 */
public record CreditCardDetails(
    String cardNumber,
    String cardholderName,
    String expiryDate,
    String cvv
) implements PaymentDetails {
    
    public CreditCardDetails {
        if (cardNumber == null || cardNumber.isBlank()) {
            throw new IllegalArgumentException("Card number is required");
        }
        if (cardholderName == null || cardholderName.isBlank()) {
            throw new IllegalArgumentException("Cardholder name is required");
        }
    }
}

