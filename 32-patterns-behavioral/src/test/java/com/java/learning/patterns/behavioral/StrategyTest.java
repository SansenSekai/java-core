package com.java.learning.patterns.behavioral;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Strategy Pattern")
class StrategyTest {

    @Nested
    @DisplayName("Discount Strategies")
    class DiscountStrategies {
        
        @Test
        @DisplayName("NoDiscount не даёт скидку")
        void noDiscountShouldReturnZero() {
            var strategy = new Strategy.NoDiscount();
            
            assertThat(strategy.calculateDiscount(100.0)).isEqualTo(0.0);
        }
        
        @Test
        @DisplayName("PercentageDiscount вычисляет процент")
        void percentageDiscountShouldCalculatePercent() {
            var strategy = new Strategy.PercentageDiscount(10.0); // 10%
            
            assertThat(strategy.calculateDiscount(100.0)).isEqualTo(10.0);
            assertThat(strategy.calculateDiscount(50.0)).isEqualTo(5.0);
        }
        
        @Test
        @DisplayName("FixedDiscount возвращает фиксированную сумму")
        void fixedDiscountShouldReturnFixedAmount() {
            var strategy = new Strategy.FixedDiscount(15.0);
            
            assertThat(strategy.calculateDiscount(100.0)).isEqualTo(15.0);
            // Не должна превышать цену
            assertThat(strategy.calculateDiscount(10.0)).isEqualTo(10.0);
        }
    }
    
    @Nested
    @DisplayName("ShoppingCart")
    class ShoppingCartTest {
        
        @Test
        @DisplayName("Вычисляет итого без скидки")
        void shouldCalculateTotalWithoutDiscount() {
            var cart = new Strategy.ShoppingCart();
            cart.addItem(100.0);
            cart.addItem(50.0);
            cart.addItem(30.0);
            
            assertThat(cart.calculateTotal()).isEqualTo(180.0);
        }
        
        @Test
        @DisplayName("Применяет процентную скидку")
        void shouldApplyPercentageDiscount() {
            var cart = new Strategy.ShoppingCart();
            cart.addItem(100.0);
            cart.setDiscountStrategy(new Strategy.PercentageDiscount(10.0));
            
            assertThat(cart.calculateTotal()).isEqualTo(90.0);
        }
        
        @Test
        @DisplayName("Можно менять стратегию")
        void shouldAllowChangingStrategy() {
            var cart = new Strategy.ShoppingCart();
            cart.addItem(100.0);
            
            cart.setDiscountStrategy(new Strategy.PercentageDiscount(10.0));
            assertThat(cart.calculateTotal()).isEqualTo(90.0);
            
            cart.setDiscountStrategy(new Strategy.FixedDiscount(25.0));
            assertThat(cart.calculateTotal()).isEqualTo(75.0);
            
            cart.setDiscountStrategy(new Strategy.NoDiscount());
            assertThat(cart.calculateTotal()).isEqualTo(100.0);
        }
    }
}

