package com.java.learning.solid.ocp.solution;

import java.math.BigDecimal;

/**
 * Калькулятор цен, использующий стратегии скидок.
 * 
 * Этот класс закрыт для модификации: добавление новых скидок
 * не требует изменения этого кода.
 * 
 * TODO: Реализуй этот класс
 */
public class PriceCalculator {
    
    // TODO: Добавь поле для стратегии скидки по умолчанию
    
    public PriceCalculator() {
        // TODO: Инициализируй стратегию по умолчанию (NoDiscount)
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Вычисляет цену с применением указанной скидки.
     */
    public BigDecimal calculate(BigDecimal originalPrice, DiscountStrategy discount) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Вычисляет цену без скидки.
     */
    public BigDecimal calculate(BigDecimal originalPrice) {
        // TODO: Реализуй метод (используй стратегию по умолчанию)
        throw new UnsupportedOperationException("TODO: implement");
    }
}

