package com.java.learning.sync;

/**
 * Потокобезопасный счётчик.
 * 
 * Демонстрирует разные способы синхронизации:
 * 1. synchronized методы
 * 2. synchronized блоки
 * 3. volatile (недостаточно для инкремента!)
 * 
 * TODO: Реализуй все три варианта и сравни
 */
public class ThreadSafeCounter {
    
    private int value;
    private final Object lock = new Object();
    
    /**
     * Инкремент с synchronized методом.
     */
    public synchronized void incrementWithSyncMethod() {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Инкремент с synchronized блоком.
     */
    public void incrementWithSyncBlock() {
        // TODO: Реализуй с synchronized(lock)
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Получение значения.
     */
    public synchronized int getValue() {
        return value;
    }
    
    /**
     * Атомарный инкремент и получение предыдущего значения.
     * 
     * Аналог AtomicInteger.getAndIncrement()
     */
    public synchronized int getAndIncrement() {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Атомарное сравнение и установка.
     * 
     * Аналог AtomicInteger.compareAndSet()
     * 
     * @return true если значение было изменено
     */
    public synchronized boolean compareAndSet(int expected, int newValue) {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
}

