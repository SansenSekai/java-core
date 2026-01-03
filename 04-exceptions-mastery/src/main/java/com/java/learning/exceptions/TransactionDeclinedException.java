package com.java.learning.exceptions;

/**
 * Исключение: транзакция отклонена.
 * 
 * TODO: Реализуй это исключение полностью
 */
public class TransactionDeclinedException extends TransactionException {
    
    // TODO: Добавь поле для кода отклонения
    private final DeclineReason reason;
    
    public enum DeclineReason {
        FRAUD_SUSPECTED,
        LIMIT_EXCEEDED,
        INVALID_RECIPIENT,
        SYSTEM_ERROR
    }
    
    public TransactionDeclinedException(String transactionId, DeclineReason reason) {
        super("Transaction " + transactionId + " declined: " + reason, transactionId);
        this.reason = reason;
        // TODO: Добавь больше контекста в сообщение
    }
    
    public DeclineReason getReason() {
        return reason;
        // TODO: Проверь, что это правильная реализация
    }
}
