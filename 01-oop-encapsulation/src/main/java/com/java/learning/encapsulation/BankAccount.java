package com.java.learning.encapsulation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Банковский счёт с защитой инвариантов.
 * 
 * Инварианты:
 * - Баланс никогда не может быть отрицательным
 * - История транзакций не может быть изменена извне
 * - Каждая операция записывается в историю
 * 
 * TODO: Реализуй этот класс согласно требованиям в README.md
 */
public class BankAccount {
    
    // TODO: Определи необходимые поля
    // Подсказка: подумай о типе для баланса (double? BigDecimal?)
    // Подсказка: как хранить историю транзакций?
    
    /**
     * Создаёт новый банковский счёт с указанным начальным балансом.
     * 
     * @param initialBalance начальный баланс (должен быть >= 0)
     * @throws IllegalArgumentException если initialBalance < 0
     */
    public BankAccount(BigDecimal initialBalance) {
        // TODO: Реализуй конструктор
        // - Проверь, что initialBalance не null и >= 0
        // - Инициализируй поля
        // - Добавь первую запись в историю (открытие счёта)
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Возвращает текущий баланс счёта.
     */
    public BigDecimal getBalance() {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Пополняет счёт на указанную сумму.
     * 
     * @param amount сумма пополнения (должна быть > 0)
     * @throws IllegalArgumentException если amount <= 0 или null
     */
    public void deposit(BigDecimal amount) {
        // TODO: Реализуй метод
        // - Проверь корректность amount
        // - Увеличь баланс
        // - Добавь запись в историю
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Снимает указанную сумму со счёта.
     * 
     * @param amount сумма снятия (должна быть > 0 и <= balance)
     * @throws IllegalArgumentException если amount <= 0, null, или превышает баланс
     */
    public void withdraw(BigDecimal amount) {
        // TODO: Реализуй метод
        // - Проверь корректность amount
        // - Проверь, что достаточно средств
        // - Уменьши баланс
        // - Добавь запись в историю
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Возвращает историю транзакций.
     * 
     * ВАЖНО: Возвращённый список не должен позволять изменять
     * внутреннее состояние объекта!
     * 
     * @return неизменяемый список транзакций
     */
    public List<Transaction> getTransactionHistory() {
        // TODO: Реализуй метод
        // Подсказка: что произойдёт, если вернуть просто ссылку на внутренний список?
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Запись о транзакции.
     */
    public record Transaction(
        LocalDateTime timestamp,
        TransactionType type,
        BigDecimal amount,
        BigDecimal balanceAfter
    ) {}
    
    public enum TransactionType {
        ACCOUNT_OPENED,
        DEPOSIT,
        WITHDRAWAL
    }
}

