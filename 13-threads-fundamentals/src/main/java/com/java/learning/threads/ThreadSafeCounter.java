package com.java.learning.threads;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Потокобезопасный счётчик.
 * 
 * Реализуй несколько версий:
 * 1. С synchronized
 * 2. С AtomicInteger
 * 3. С volatile (покажи, почему это НЕ работает!)
 * 
 * TODO: Реализуй все версии
 */
public class ThreadSafeCounter {
    
    /**
     * Версия с synchronized.
     */
    public static class SynchronizedCounter {
        private int value = 0;
        
        public synchronized void increment() {
            // TODO: Реализуй
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        public synchronized void decrement() {
            // TODO: Реализуй
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        public synchronized int get() {
            return value;
        }
        
        /**
         * Атомарный инкремент только если текущее значение равно expected.
         */
        public synchronized boolean compareAndIncrement(int expected) {
            // TODO: Реализуй
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
    
    /**
     * Версия с AtomicInteger (рекомендуется).
     */
    public static class AtomicCounter {
        private final AtomicInteger value = new AtomicInteger(0);
        
        public void increment() {
            // TODO: Реализуй
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        public void decrement() {
            // TODO: Реализуй
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        public int get() {
            return value.get();
        }
        
        public boolean compareAndIncrement(int expected) {
            // TODO: Реализуй с CAS
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
    
    /**
     * НЕПРАВИЛЬНАЯ версия с volatile.
     * 
     * Демонстрирует, почему volatile недостаточно для составных операций.
     * Этот класс НЕ потокобезопасен!
     */
    public static class BrokenVolatileCounter {
        private volatile int value = 0;
        
        // ПЛОХО: volatile не делает ++ атомарным!
        public void increment() {
            value++; // Это: read, increment, write — три операции!
        }
        
        public void decrement() {
            value--;
        }
        
        public int get() {
            return value;
        }
    }
}

