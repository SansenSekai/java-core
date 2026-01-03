package com.java.learning.solid.ocp.problem;

import java.math.BigDecimal;

/**
 * ПРОБЛЕМА: Этот класс нарушает OCP.
 * 
 * При добавлении нового типа скидки нужно:
 * 1. Добавить новый enum
 * 2. Добавить новый case в switch
 * 
 * Класс НЕ закрыт для модификации.
 * 
 * ЗАДАНИЕ: Рефактори этот код в пакете ocp.solution,
 * используя паттерн Strategy или подобный подход.
 */
public class DiscountCalculator {
    
    public enum DiscountType {
        NONE,
        SEASONAL,
        VIP,
        PROMO_CODE,
        BULK
        // Что если добавить LOYALTY, STUDENT, EMPLOYEE...?
    }
    
    /**
     * Вычисляет цену со скидкой.
     */
    public BigDecimal calculatePrice(BigDecimal originalPrice, DiscountType discountType) {
        return switch (discountType) {
            case NONE -> originalPrice;
            
            case SEASONAL -> {
                // 15% скидка
                BigDecimal discount = originalPrice.multiply(new BigDecimal("0.15"));
                yield originalPrice.subtract(discount);
            }
            
            case VIP -> {
                // 20% скидка
                BigDecimal discount = originalPrice.multiply(new BigDecimal("0.20"));
                yield originalPrice.subtract(discount);
            }
            
            case PROMO_CODE -> {
                // Фиксированная скидка $10
                BigDecimal discount = new BigDecimal("10.00");
                BigDecimal result = originalPrice.subtract(discount);
                yield result.compareTo(BigDecimal.ZERO) > 0 ? result : BigDecimal.ZERO;
            }
            
            case BULK -> {
                // 25% скидка для оптовых покупок
                BigDecimal discount = originalPrice.multiply(new BigDecimal("0.25"));
                yield originalPrice.subtract(discount);
            }
        };
    }
}

