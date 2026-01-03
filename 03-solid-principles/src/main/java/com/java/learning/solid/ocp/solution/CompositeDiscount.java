package com.java.learning.solid.ocp.solution;

import java.math.BigDecimal;
import java.util.List;

/**
 * Комбинированная скидка — применяет несколько скидок последовательно.
 * 
 * TODO: Реализуй этот класс
 */
public class CompositeDiscount implements DiscountStrategy {
    
    // TODO: Добавь поле для списка скидок
    
    /**
     * Создаёт комбинированную скидку.
     * 
     * @param discounts список скидок для последовательного применения
     */
    public CompositeDiscount(List<DiscountStrategy> discounts) {
        // TODO: Реализуй конструктор
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public CompositeDiscount(DiscountStrategy... discounts) {
        this(List.of(discounts));
    }
    
    @Override
    public BigDecimal apply(BigDecimal originalPrice) {
        // TODO: Применяй скидки последовательно
        // Результат предыдущей скидки — вход для следующей
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public String getName() {
        return "Composite Discount";
    }
}

