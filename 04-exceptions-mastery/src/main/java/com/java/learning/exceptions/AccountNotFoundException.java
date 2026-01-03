package com.java.learning.exceptions;

/**
 * Исключение: счёт не найден.
 * 
 * TODO: Реализуй это исключение полностью
 */
public class AccountNotFoundException extends AccountException {
    
    public AccountNotFoundException(String accountNumber) {
        super("Account not found: " + accountNumber, accountNumber);
        // TODO: Улучши сообщение, сделай его более информативным
    }
}
