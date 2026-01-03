package com.java.learning.locks;

import java.util.concurrent.locks.*;

/**
 * Ограниченная блокирующая очередь на ReentrantLock и Condition.
 * 
 * Демонстрирует преимущества Lock/Condition над synchronized/wait/notify:
 * - Можно иметь несколько Condition для одного Lock
 * - tryLock() для неблокирующей попытки
 * - lockInterruptibly() для прерываемого захвата
 * 
 * TODO: Реализуй очередь
 * 
 * @param <E> тип элементов
 */
public class BoundedBlockingQueue<E> {
    
    private final Object[] items;
    private int count;
    private int putIndex;
    private int takeIndex;
    
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    
    public BoundedBlockingQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException();
        this.items = new Object[capacity];
    }
    
    /**
     * Добавляет элемент. Блокируется, если очередь полна.
     */
    public void put(E element) throws InterruptedException {
        // TODO: Реализуй
        // 1. lock.lockInterruptibly()
        // 2. while (count == items.length) notFull.await()
        // 3. Добавь элемент
        // 4. notEmpty.signal()
        // 5. unlock в finally!
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Извлекает элемент. Блокируется, если очередь пуста.
     */
    @SuppressWarnings("unchecked")
    public E take() throws InterruptedException {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Пытается добавить элемент без ожидания.
     * 
     * @return true если добавлен
     */
    public boolean offer(E element) {
        // TODO: Реализуй с tryLock()
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Пытается извлечь элемент без ожидания.
     */
    @SuppressWarnings("unchecked")
    public E poll() {
        // TODO: Реализуй с tryLock()
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public int size() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}

