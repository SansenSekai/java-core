package com.java.learning.solid.ocp.solution;

import java.math.BigDecimal;

/**
 * Процентная скидка.
 * 
 * TODO: Реализуй этот класс
 */
public class PercentageDiscount implements DiscountStrategy {
    
    // TODO: Добавь поле для процента скидки и названия
    
    /**
     * Создаёт процентную скидку.
     * 
     * @param name название скидки
     * @param percentage процент скидки (0-100)
     * @throws IllegalArgumentException если percentage < 0 или > 100
     */
    public PercentageDiscount(String name, int percentage) {
        // TODO: Реализуй конструктор
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public BigDecimal apply(BigDecimal originalPrice) {
        // TODO: Реализуй применение процентной скидки
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public String getName() {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public String getDescription() {
        // TODO: Вернуть "X% off"
        throw new UnsupportedOperationException("TODO: implement");
    }
}

