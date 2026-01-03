package com.java.learning.inheritance.payment;

/**
 * Интерфейс для обработки платежей.
 * 
 * Демонстрирует полиморфизм через интерфейсы.
 */
public interface PaymentProcessor {
    
    /**
     * Обрабатывает платёж.
     * 
     * @param payment платёж для обработки
     * @return результат обработки
     */
    PaymentResult process(Payment payment);
    
    /**
     * Проверяет, поддерживает ли процессор данный способ оплаты.
     */
    boolean supports(PaymentMethod method);
}

