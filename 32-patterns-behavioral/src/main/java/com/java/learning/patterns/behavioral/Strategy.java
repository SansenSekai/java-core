package com.java.learning.patterns.behavioral;

import java.util.*;

/**
 * Паттерн Strategy — выбор алгоритма в runtime.
 * 
 * Позволяет заменять алгоритм без изменения клиентского кода.
 * 
 * Примеры в JDK:
 * - Comparator — стратегия сравнения
 * - java.util.function — функциональные стратегии
 * 
 * TODO: Реализуй стратегии ценообразования
 */
public class Strategy {
    
    /**
     * Стратегия расчёта скидки.
     */
    @FunctionalInterface
    public interface DiscountStrategy {
        /**
         * Вычисляет скидку.
         * 
         * @param originalPrice исходная цена
         * @return размер скидки
         */
        double calculateDiscount(double originalPrice);
    }
    
    /**
     * Без скидки.
     */
    public static class NoDiscount implements DiscountStrategy {
        @Override
        public double calculateDiscount(double originalPrice) {
            // TODO: Реализуй
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
    
    /**
     * Процентная скидка.
     */
    public static class PercentageDiscount implements DiscountStrategy {
        private final double percentage;
        
        public PercentageDiscount(double percentage) {
            // TODO: Валидация (0-100)
            this.percentage = percentage;
        }
        
        @Override
        public double calculateDiscount(double originalPrice) {
            // TODO: Реализуй
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
    
    /**
     * Фиксированная скидка.
     */
    public static class FixedDiscount implements DiscountStrategy {
        private final double amount;
        
        public FixedDiscount(double amount) {
            this.amount = amount;
        }
        
        @Override
        public double calculateDiscount(double originalPrice) {
            // TODO: Реализуй (не больше цены!)
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
    
    /**
     * Скидка "Купи N, получи M бесплатно".
     */
    public static class BuyNGetMFree implements DiscountStrategy {
        private final int buyCount;
        private final int freeCount;
        private final double itemPrice;
        
        public BuyNGetMFree(int buyCount, int freeCount, double itemPrice) {
            this.buyCount = buyCount;
            this.freeCount = freeCount;
            this.itemPrice = itemPrice;
        }
        
        @Override
        public double calculateDiscount(double originalPrice) {
            // TODO: Реализуй
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
    
    /**
     * Контекст, использующий стратегию.
     */
    public static class ShoppingCart {
        private final List<Double> items = new ArrayList<>();
        private DiscountStrategy discountStrategy = new NoDiscount();
        
        public void addItem(double price) {
            items.add(price);
        }
        
        public void setDiscountStrategy(DiscountStrategy strategy) {
            // TODO: Реализуй
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        public double calculateTotal() {
            // TODO: Реализуй
            // total = sum(items) - discount
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
}

