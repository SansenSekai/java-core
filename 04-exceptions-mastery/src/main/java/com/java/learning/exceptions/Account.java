package com.java.learning.exceptions;

import java.math.BigDecimal;

/**
 * Банковский счёт для демонстрации работы с исключениями.
 */
public class Account {
    
    private final String accountNumber;
    private BigDecimal balance;
    private boolean blocked;
    private String blockReason;
    
    public Account(String accountNumber, BigDecimal initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.blocked = false;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public BigDecimal getBalance() {
        return balance;
    }
    
    public boolean isBlocked() {
        return blocked;
    }
    
    public String getBlockReason() {
        return blockReason;
    }
    
    public void block(String reason) {
        this.blocked = true;
        this.blockReason = reason;
    }
    
    public void unblock() {
        this.blocked = false;
        this.blockReason = null;
    }
    
    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }
    
    public void withdraw(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }
}

