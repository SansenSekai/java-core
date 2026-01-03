package com.java.learning.exceptions;

/**
 * Исключение, связанное с транзакциями.
 * 
 * TODO: Реализуй это исключение полностью
 */
public class TransactionException extends BankingException {
    
    // TODO: Добавь поле для ID транзакции
    private final String transactionId;
    
    public TransactionException(String message, String transactionId) {
        super(message);
        this.transactionId = transactionId;
        // TODO: Улучши сообщение, включив ID транзакции
    }
    
    public TransactionException(String message, String transactionId, Throwable cause) {
        super(message, cause);
        this.transactionId = transactionId;
        // TODO: Реализуй конструктор полностью
    }
    
    public String getTransactionId() {
        return transactionId;
        // TODO: Проверь, что это правильная реализация
    }
}
