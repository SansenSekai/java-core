package com.java.learning.solid.ocp.solution;

import java.math.BigDecimal;

/**
 * Без скидки — возвращает цену без изменений.
 * 
 * TODO: Реализуй этот класс
 */
public class NoDiscount implements DiscountStrategy {
    
    @Override
    public BigDecimal apply(BigDecimal originalPrice) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public String getName() {
        return "No Discount";
    }
}

