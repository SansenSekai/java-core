package com.java.learning.inheritance.payment;

/**
 * Детали платежа криптовалютой.
 */
public record CryptoDetails(
    String walletAddress,
    String cryptoCurrency
) implements PaymentDetails {
    
    public CryptoDetails {
        if (walletAddress == null || walletAddress.isBlank()) {
            throw new IllegalArgumentException("Wallet address is required");
        }
        if (cryptoCurrency == null || cryptoCurrency.isBlank()) {
            throw new IllegalArgumentException("Cryptocurrency is required");
        }
    }
}

