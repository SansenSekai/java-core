package com.java.learning.solid.ocp.solution;

import java.math.BigDecimal;

/**
 * Фиксированная скидка (в денежных единицах).
 * 
 * TODO: Реализуй этот класс
 */
public class FixedAmountDiscount implements DiscountStrategy {
    
    // TODO: Добавь поля для суммы скидки и названия
    
    /**
     * Создаёт фиксированную скидку.
     * 
     * @param name название скидки
     * @param amount сумма скидки (> 0)
     * @throws IllegalArgumentException если amount <= 0
     */
    public FixedAmountDiscount(String name, BigDecimal amount) {
        // TODO: Реализуй конструктор
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public BigDecimal apply(BigDecimal originalPrice) {
        // TODO: Реализуй применение фиксированной скидки
        // Не должно уходить в минус!
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public String getName() {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public String getDescription() {
        // TODO: Вернуть "$X off"
        throw new UnsupportedOperationException("TODO: implement");
    }
}

