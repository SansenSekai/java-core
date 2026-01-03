package com.java.learning.inheritance.payment;

import java.util.Optional;

/**
 * Результат обработки платежа.
 */
public record PaymentResult(
    boolean success,
    String transactionId,
    String message,
    ErrorCode errorCode
) {
    
    public static PaymentResult success(String transactionId) {
        return new PaymentResult(true, transactionId, "Payment processed successfully", null);
    }
    
    public static PaymentResult failure(String message, ErrorCode errorCode) {
        return new PaymentResult(false, null, message, errorCode);
    }
    
    public Optional<String> getTransactionId() {
        return Optional.ofNullable(transactionId);
    }
    
    public Optional<ErrorCode> getErrorCode() {
        return Optional.ofNullable(errorCode);
    }
    
    public enum ErrorCode {
        INVALID_CARD_NUMBER,
        INVALID_EMAIL,
        INVALID_WALLET_ADDRESS,
        INSUFFICIENT_FUNDS,
        PAYMENT_DECLINED,
        NETWORK_ERROR,
        UNKNOWN_ERROR
    }
}

