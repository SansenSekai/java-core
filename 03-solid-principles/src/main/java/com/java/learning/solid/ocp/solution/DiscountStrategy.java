package com.java.learning.solid.ocp.solution;

import java.math.BigDecimal;

/**
 * Стратегия расчёта скидки.
 * 
 * Следуя OCP, новые типы скидок добавляются путём создания
 * новых реализаций этого интерфейса, без изменения существующего кода.
 */
public interface DiscountStrategy {
    
    /**
     * Вычисляет итоговую цену с учётом скидки.
     * 
     * @param originalPrice исходная цена
     * @return цена со скидкой
     */
    BigDecimal apply(BigDecimal originalPrice);
    
    /**
     * Возвращает название скидки.
     */
    String getName();
    
    /**
     * Возвращает описание скидки для отображения пользователю.
     */
    default String getDescription() {
        return getName();
    }
}

